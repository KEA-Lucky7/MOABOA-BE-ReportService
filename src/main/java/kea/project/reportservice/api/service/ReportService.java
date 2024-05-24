package kea.project.reportservice.api.service;

import kea.project.reportservice.api.controller.dto.response.GetReportResponse;
import kea.project.reportservice.api.service.dto.GetReportServiceDto;

public interface ReportService {

    GetReportResponse getReport(GetReportServiceDto dto);
}
