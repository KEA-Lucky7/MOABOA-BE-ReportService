package kea.project.reportservice.api.controller;

import kea.project.reportservice.ControllerTestSupport;
import kea.project.reportservice.api.controller.dto.response.GetReportResponse;
import kea.project.reportservice.api.service.dto.GetReportServiceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReportControllerTest extends ControllerTestSupport {

    @DisplayName(value = "레포트를 조회한다.")
    @Test
    public void GetReport() throws Exception {
        when(authServiceClient.validateToken()).thenReturn(ResponseEntity.ok(1L));

        String token = "token";
        //when
        mockMvc.perform(
                        get("/reports/compare")
                                .header("Authorization",token)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}