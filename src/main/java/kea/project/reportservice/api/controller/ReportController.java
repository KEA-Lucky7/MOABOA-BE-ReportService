package kea.project.reportservice.api.controller;

import kea.project.reportservice.api.service.ReportCommandService;
import kea.project.reportservice.api.service.ReportQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    private final ReportQueryService queryService;
    private final ReportCommandService commandService;

}
