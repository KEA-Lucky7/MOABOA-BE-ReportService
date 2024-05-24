package kea.project.reportservice;

import kea.project.reportservice.domain.report.repository.ReportRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public abstract class IntegrationTestSupport {

    @Autowired
    protected ReportRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAllInBatch();
    }
}
