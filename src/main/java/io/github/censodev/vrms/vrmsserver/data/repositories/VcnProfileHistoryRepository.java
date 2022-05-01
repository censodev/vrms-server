package io.github.censodev.vrms.vrmsserver.data.repositories;

import io.github.censodev.vrms.vrmsserver.data.domains.VcnProfileHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VcnProfileHistoryRepository extends JpaRepository<VcnProfileHistory, Long> {
    List<VcnProfileHistory> findByVcnProfileId(Long vcnProfileId);
}