package io.github.censodev.vrms.vrmsserver.data.repositories;

import io.github.censodev.vrms.vrmsserver.data.models.MstDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MstDistrictRepository extends JpaRepository<MstDistrict, Integer> {
    List<MstDistrict> findByProvinceId(Integer provinceId);
}