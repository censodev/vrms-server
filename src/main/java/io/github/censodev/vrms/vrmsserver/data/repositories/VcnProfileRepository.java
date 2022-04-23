package io.github.censodev.vrms.vrmsserver.data.repositories;

import io.github.censodev.vrms.vrmsserver.data.domains.VcnProfile;
import io.github.censodev.vrms.vrmsserver.data.repositories.custom.VcnProfileCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VcnProfileRepository extends JpaRepository<VcnProfile, Long>, VcnProfileCustomRepository {
    Page<VcnProfile> findByPatientProfileId(Long patientProfileId, Pageable pageable);

    Page<VcnProfile> findByCreatedById(Long createdById, Pageable pageable);
}