package kea.project.reportservice;

import kea.project.reportservice.api.service.ReportService;
import kea.project.reportservice.domain.report.repository.ReportRepository;
import kea.project.reportservice.feign.AuthServiceClient;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public abstract class IntegrationTestSupport {

    @Autowired
    protected ReportService reportService;

    @Autowired
    protected ReportRepository reportRepository;

    @MockBean
    private AuthServiceClient authServiceClient;
    @AfterEach
    void tearDown() {
        reportRepository.deleteAllInBatch();
    }
}
