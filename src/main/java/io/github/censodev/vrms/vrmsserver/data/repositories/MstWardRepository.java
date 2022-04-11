package io.github.censodev.vrms.vrmsserver.data.repositories;

import io.github.censodev.vrms.vrmsserver.data.domains.MstWard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MstWardRepository extends JpaRepository<MstWard, Integer> {
    List<MstWard> findByDistrictId(Integer districtId);
}