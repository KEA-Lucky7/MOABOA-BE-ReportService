package kea.project.reportservice.domain.report.vo;

import lombok.Getter;

@Getter
public enum WalletType {

    FOOD("식비"),
    TRAFFIC("교통"),
    LEISURE("여가"),
    EDUCATION("교육"),
    LIFE("생활"),
    FINANCE("금융");

    private final String name;

    WalletType(String name) {
        this.name = name;
    }
}
