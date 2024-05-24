package kea.project.reportservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import kea.project.reportservice.api.controller.ReportController;
import kea.project.reportservice.api.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
    ReportController.class,

})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected ReportService reportService;
}
