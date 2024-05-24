package kea.project.reportservice.api.service;

import kea.project.reportservice.api.controller.dto.response.GetReportResponse;
import kea.project.reportservice.api.service.dto.GetReportServiceDto;
import kea.project.reportservice.domain.report.projection.GetReportProjection;
import kea.project.reportservice.domain.report.repository.ReportRepository;
import kea.project.reportservice.domain.report.vo.ReportEntityState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public GetReportResponse getReport(GetReportServiceDto dto) {
        List<GetReportProjection> lastMonthReport = findReportBy(dto.getMemberId(), dto.getDate().minusMonths(1));
        List<GetReportProjection> thisMonthReport = findReportBy(dto.getMemberId(), dto.getDate());
        return GetReportResponse.service(lastMonthReport,thisMonthReport);
    }

    private List<GetReportProjection> findReportBy(Long memberId, LocalDate localDate) {
        return reportRepository.findAllByMemberIdAndConsumedDateAndReportEntityState(memberId, localDate, ReportEntityState.ACTIVE);
    }

}
