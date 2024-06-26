package kea.project.reportservice.domain.report.repository;


import feign.ResponseMapper;
import kea.project.reportservice.domain.report.entity.ReportEntity;
import kea.project.reportservice.domain.report.projection.GetReportProjection;
import kea.project.reportservice.domain.report.vo.ReportEntityState;
import kea.project.reportservice.domain.report.vo.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

    List<GetReportProjection> findAllByMemberIdAndConsumedDateAndReportEntityState(Long memberId, LocalDate localDate, ReportEntityState reportEntityState);

    Optional<ReportEntity> findByMemberIdAndConsumedDateAndTypeAndReportEntityState(Long memberId, LocalDate localDate, WalletType walletType, ReportEntityState reportEntityState);
}