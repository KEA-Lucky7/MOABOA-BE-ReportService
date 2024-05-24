package kea.project.reportservice;

import kea.project.reportservice.api.service.ReportService;
import kea.project.reportservice.domain.report.repository.ReportRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class IntegrationTestSupport {

    @Autowired
    protected ReportService reportService;

    @Autowired
    protected ReportRepository reportRepository;

    @AfterEach
    void tearDown() {
        reportRepository.deleteAllInBatch();
    }
}
