package io.github.censodev.vrms.vrmsserver.data.repositories;

import io.github.censodev.vrms.vrmsserver.data.models.VcnProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VcnProfileRepository extends JpaRepository<VcnProfile, Long> {
}