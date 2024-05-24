package kea.project.reportservice.api.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetReportList {
    private final Integer amount;
    private final String type;

    @Builder
    private GetReportList(Integer amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public static GetReportList of(Integer amount, String type) {
        return GetReportList.builder()
                .amount(amount)
                .type(type)
                .build();
    }
}
