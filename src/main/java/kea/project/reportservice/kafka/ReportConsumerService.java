package kea.project.reportservice.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kea.project.reportservice.domain.report.entity.ReportEntity;
import kea.project.reportservice.domain.report.repository.ReportRepository;
import kea.project.reportservice.domain.report.vo.ReportEntityState;
import kea.project.reportservice.domain.report.vo.WalletType;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReportConsumerService {

    private final ReportRepository reportRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.topic.post.name}", groupId = "${spring.kafka.consumer.group-id}")
    @Transactional
    public void consume(String message) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(message);
        String operation = jsonNode.get("op") != null ? jsonNode.get("op").asText() : null;
        String databaseName = jsonNode.get("source").get("db") != null ? jsonNode.get("source").get("db").asText() : null;
        String tableName = jsonNode.get("source").get("table") != null ? jsonNode.get("source").get("table").asText() : null;
        if(operation==null || databaseName==null || tableName==null)
            return;
        if(!databaseName.equals("post")||!tableName.equals("wallet"))
            return;
        switch (operation) {
            case "c":
                updateReport(jsonNode.get("after"),operation);
                break;
            case "d":
                updateReport(jsonNode.get("before"),operation);
                break;
        }
    }

    private void updateReport(JsonNode node, String operation) {
        Long memberId = node.get("member_id").asLong();
        WalletType walletType = WalletType.valueOf(node.get("wallet_type").asText());
        LocalDate consumedDate = LocalDate.parse(node.get("consumed_date").asText());
        int amount;
        if(operation.equals("c"))
            amount = node.get("amount").asInt();
        else if(operation.equals("d"))
            amount = -node.get("amount").asInt();
        else
            amount = 0;
        ReportEntity report = reportRepository.findByMemberIdAndConsumedDateAndTypeAndReportEntityState(memberId, consumedDate, walletType, ReportEntityState.ACTIVE)
                .map(existingReport -> {
                    existingReport.updateAmount(amount);
                    return existingReport;
                })
                .orElseGet(() -> ReportEntity.of(memberId, walletType, amount, consumedDate, ReportEntityState.ACTIVE));

        reportRepository.save(report);
    }
}
