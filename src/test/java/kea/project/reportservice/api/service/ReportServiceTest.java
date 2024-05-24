package kea.project.reportservice.api.service;

import kea.project.reportservice.IntegrationTestSupport;
import kea.project.reportservice.api.controller.dto.response.GetReportResponse;
import kea.project.reportservice.api.service.dto.GetReportServiceDto;
import kea.project.reportservice.domain.report.entity.ReportEntity;
import kea.project.reportservice.domain.report.vo.ReportEntityState;
import kea.project.reportservice.domain.report.vo.WalletType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class ReportServiceTest extends IntegrationTestSupport {

    @DisplayName(value = "유저는 저번달의 오늘과 오늘을 비교한 레포트를 확인한다.")
    @Test
    public void getReport() {
        //given
        Long memberId = 1L;
        LocalDate date = LocalDate.of(2024, 5, 24);
        GetReportServiceDto serviceDto = GetReportServiceDto.of(memberId, date);
        LocalDate today = LocalDate.of(2024, 5, 24);
        LocalDate todayOfLastMonth = LocalDate.of(2024, 4, 24);
        ReportEntity todayReportEntity1 = createReport(memberId, WalletType.EDUCATION, 1000, today, ReportEntityState.ACTIVE);
        ReportEntity todayReportEntity2 = createReport(memberId, WalletType.FINANCE, 2000, today, ReportEntityState.ACTIVE);
        ReportEntity todayReportEntity3 = createReport(memberId, WalletType.FOOD, 3000, today, ReportEntityState.ACTIVE);
        ReportEntity todayReportEntity4 = createReport(memberId, WalletType.LIFE, 4000, today, ReportEntityState.ACTIVE);
        ReportEntity todayReportEntity5 = createReport(memberId, WalletType.LEISURE, 5000, today, ReportEntityState.ACTIVE);
        ReportEntity todayReportEntity6 = createReport(memberId, WalletType.TRAFFIC, 6000, today, ReportEntityState.ACTIVE);
        ReportEntity todayOfLastMonthReportEntity1 = createReport(memberId, WalletType.EDUCATION, 7000, todayOfLastMonth, ReportEntityState.ACTIVE);
        ReportEntity todayOfLastMonthReportEntity2 = createReport(memberId, WalletType.FINANCE, 8000, todayOfLastMonth, ReportEntityState.ACTIVE);
        ReportEntity todayOfLastMonthReportEntity3 = createReport(memberId, WalletType.FOOD, 9000, todayOfLastMonth, ReportEntityState.ACTIVE);
        ReportEntity todayOfLastMonthReportEntity4 = createReport(memberId, WalletType.LIFE, 10000, todayOfLastMonth, ReportEntityState.ACTIVE);
        ReportEntity todayOfLastMonthReportEntity5 = createReport(memberId, WalletType.LEISURE, 11000, todayOfLastMonth, ReportEntityState.ACTIVE);
        ReportEntity todayOfLastMonthReportEntity6 = createReport(memberId, WalletType.TRAFFIC, 12000, todayOfLastMonth, ReportEntityState.ACTIVE);
        reportRepository.saveAll(List.of(todayReportEntity1, todayReportEntity2, todayReportEntity3, todayReportEntity4, todayReportEntity5, todayReportEntity6));
        reportRepository.saveAll(List.of(todayOfLastMonthReportEntity1, todayOfLastMonthReportEntity2, todayOfLastMonthReportEntity3, todayOfLastMonthReportEntity4, todayOfLastMonthReportEntity5, todayOfLastMonthReportEntity6));

        //when
        List<ReportEntity> reportEntities = reportRepository.findAll();
        System.out.println(reportEntities);
        GetReportResponse response = reportService.getReport(serviceDto);

        //then
        assertThat(response).isNotNull();
        assertThat(response.getSameDayThisMonthAmount()).isEqualTo(21000);
        assertThat(response.getSameDayLastMonthAmount()).isEqualTo(57000);
        assertThat(response.getSameDayLastMonthReportList()).hasSize(6)
                .extracting("amount", "type")
                .containsExactlyInAnyOrder(
                        tuple(7000, "교육"),
                        tuple(8000, "금융"),
                        tuple(9000, "식비"),
                        tuple(10000, "생활"),
                        tuple(11000, "여가"),
                        tuple(12000, "교통")
                );
        assertThat(response.getSameDayThisMonthReportList()).hasSize(6)
                .extracting("amount", "type")
                .containsExactlyInAnyOrder(
                        tuple(1000, "교육"),
                        tuple(2000, "금융"),
                        tuple(3000, "식비"),
                        tuple(4000, "생활"),
                        tuple(5000, "여가"),
                        tuple(6000, "교통")
                );
    }

    public ReportEntity createReport(Long memberId, WalletType type, int amount, LocalDate localDate, ReportEntityState state) {
        return ReportEntity.of(memberId, type, amount, localDate, state);
    }
}