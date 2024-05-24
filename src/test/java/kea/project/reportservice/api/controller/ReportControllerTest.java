package kea.project.reportservice.api.controller;

import kea.project.reportservice.ControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReportControllerTest extends ControllerTestSupport {

    @DisplayName(value = "레포트를 조회한다.")
    @Test
    public void GetReport() throws Exception {
        //when
        mockMvc.perform(
                        get("/reports/compare")
                )
                .andExpect(status().isOk());
    }
}