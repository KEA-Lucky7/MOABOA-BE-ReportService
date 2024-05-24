package kea.project.reportservice.domain.report.repository;


import kea.project.reportservice.domain.report.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

}
