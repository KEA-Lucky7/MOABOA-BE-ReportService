package kea.project.reportservice.api.controller.dto.response;

import kea.project.reportservice.domain.report.entity.ReportEntity;
import kea.project.reportservice.domain.report.projection.GetReportProjection;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetReportResponse {

    private final List<GetReportList> sameDayLastMonthReportList;
    private final List<GetReportList> sameDayThisMonthReportList;
    private final Integer sameDayLastMonthAmount;
    private final Integer sameDayThisMonthAmount;

    @Builder
    private GetReportResponse(List<GetReportList> sameDayLastMonthReportList, List<GetReportList> sameDayThisMonthReportList, Integer sameDayLastMonthAmount, Integer sameDayThisMonthAmount) {
        this.sameDayLastMonthReportList = sameDayLastMonthReportList;
        this.sameDayThisMonthReportList = sameDayThisMonthReportList;
        this.sameDayLastMonthAmount = sameDayLastMonthAmount;
        this.sameDayThisMonthAmount = sameDayThisMonthAmount;
    }

    public static GetReportResponse of(List<GetReportList> sameDayLastMonthReportList, List<GetReportList> sameDayThisMonthReportList, Integer sameDayLastMonthAmount, Integer sameDayThisMonthAmount) {
        return GetReportResponse.builder()
                .sameDayThisMonthAmount(sameDayThisMonthAmount)
                .sameDayLastMonthAmount(sameDayLastMonthAmount)
                .sameDayThisMonthReportList(sameDayThisMonthReportList)
                .sameDayLastMonthReportList(sameDayLastMonthReportList)
                .build();
    }

    public static GetReportResponse service(List<GetReportProjection> lastMonthReport, List<GetReportProjection> thisMonthReport) {
        int sameDayLastMonthAmount = 0;
        int sameDayThisMonthAmount = 0;
        List<GetReportList> sameDayLastMonthReportList = new ArrayList<>();
        List<GetReportList> sameDayThisMonthReportList = new ArrayList<>();
        for (GetReportProjection projection : lastMonthReport) {
            sameDayLastMonthAmount += projection.getAmount();
            sameDayLastMonthReportList.add(GetReportList.of(projection.getAmount(), projection.getType().getName()));
        }
        for (GetReportProjection projection : thisMonthReport) {
            sameDayThisMonthAmount += projection.getAmount();
            sameDayThisMonthReportList.add(GetReportList.of(projection.getAmount(), projection.getType().getName()));
        }
        return GetReportResponse.of(sameDayLastMonthReportList, sameDayThisMonthReportList, sameDayLastMonthAmount, sameDayThisMonthAmount);
    }
}
