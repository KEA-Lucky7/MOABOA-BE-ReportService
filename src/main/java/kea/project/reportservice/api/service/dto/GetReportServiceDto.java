package kea.project.reportservice.api.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GetReportServiceDto {
    private final Long memberId;
    private final LocalDate date;

    @Builder
    private GetReportServiceDto(Long memberId, LocalDate date) {
        this.memberId = memberId;
        this.date = date;
    }

    public static GetReportServiceDto of(Long MemberId, LocalDate date){
        return GetReportServiceDto.builder()
                .memberId(MemberId)
                .date(date)
                .build();
    }
}
