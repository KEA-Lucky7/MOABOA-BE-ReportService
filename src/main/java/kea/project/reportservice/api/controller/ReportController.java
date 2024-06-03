package kea.project.reportservice.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import kea.project.reportservice.api.controller.dto.response.GetReportResponse;
import kea.project.reportservice.api.service.ReportService;
import kea.project.reportservice.api.service.dto.GetReportServiceDto;
import kea.project.reportservice.feign.AuthServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Enumeration;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;
    private final AuthServiceClient authServiceClient;
    @GetMapping("/compare")
    public GetReportResponse GetReport(){
        Long memberId = authServiceClient.validateToken().getBody();
        return reportService.getReport(GetReportServiceDto.of(memberId, LocalDate.now()));
    }
}
