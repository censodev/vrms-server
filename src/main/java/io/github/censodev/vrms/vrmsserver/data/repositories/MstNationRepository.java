package io.github.censodev.vrms.vrmsserver.data.repositories;

import io.github.censodev.vrms.vrmsserver.data.domains.MstNation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MstNationRepository extends JpaRepository<MstNation, Integer> {
}