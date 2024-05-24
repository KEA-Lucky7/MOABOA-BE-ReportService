package kea.project.reportservice.domain.report.vo;

import lombok.Getter;

@Getter
public enum ReportEntityState {

    ACTIVE("활동"), DELETE("삭제");

    private final String name;

    ReportEntityState(String name) {
        this.name = name;
    }
}
