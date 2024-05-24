package kea.project.reportservice.domain.report.projection;

import kea.project.reportservice.domain.report.vo.WalletType;

public interface GetReportProjection {
    Integer getAmount();
    WalletType getType();
}
