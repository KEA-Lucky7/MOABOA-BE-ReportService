package kea.project.reportservice.domain.report.entity;

import jakarta.persistence.*;
import kea.project.reportservice.domain.report.vo.ReportEntityState;
import kea.project.reportservice.domain.report.vo.WalletType;
import kea.project.reportservice.global.common.entity.BaseEntity;
import lombok.*;

import java.time.LocalDate;

@Entity()
@Table(name = "REPORT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
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
    private Integer amount;

    @Column(name = "consumed_date")
    private LocalDate consumedDate;

    @Column(name = "report_state")
    @Enumerated(EnumType.STRING)
    private ReportEntityState reportEntityState;

    @Builder
    public ReportEntity(Long memberId, WalletType type, Integer amount, LocalDate consumedDate, ReportEntityState reportEntityState) {
        this.memberId = memberId;
        this.type = type;
        this.amount = amount;
        this.consumedDate = consumedDate;
        this.reportEntityState = reportEntityState;
    }

    public static ReportEntity of(Long memberId, WalletType type, Integer amount, LocalDate consumedDate, ReportEntityState state) {
        return ReportEntity.builder()
                .memberId(memberId)
                .type(type)
                .amount(amount)
                .consumedDate(consumedDate)
                .reportEntityState(state)
                .build();
    }
}
