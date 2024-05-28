package kea.project.reportservice.api.controller;

import kea.project.reportservice.api.controller.dto.response.GetReportResponse;
import kea.project.reportservice.api.service.ReportService;
import kea.project.reportservice.api.service.dto.GetReportServiceDto;
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

    @GetMapping("/compare")
    public GetReportResponse GetReport(){
        //todo 인증 서비스 연결 전까지 일단 임의값 사용 추후 header에서 값 가져오는 걸로 변경
        Long memberId = 1L;
        return reportService.getReport(GetReportServiceDto.of(memberId, LocalDate.now()));
    }
}
