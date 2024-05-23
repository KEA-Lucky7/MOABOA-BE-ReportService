package kea.project.reportservice.domain.report.entity;

import jakarta.persistence.*;
import kea.project.reportservice.domain.report.vo.ReportEntityState;
import kea.project.reportservice.domain.report.vo.WalletType;
import kea.project.reportservice.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "report")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReportEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WalletType type;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "report_state")
    @Enumerated(EnumType.STRING)
    private ReportEntityState reportEntityState;

}
