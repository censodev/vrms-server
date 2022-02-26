package io.github.censodev.vrms.vrmsserver.data.repositories;

import io.github.censodev.vrms.vrmsserver.data.models.VcnPackage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VcnPackageRepository extends JpaRepository<VcnPackage, Long> {
    Optional<VcnPackage> findByDiseasesCode(String diseasesCode);

    Optional<VcnPackage> findByDiseasesCodeAndIdNot(String diseasesCode, Long id);

    @Query("select s from VcnPackage s where s.diseasesCode like :kw")
    Page<VcnPackage> search(@Param("kw") String keyword, Pageable pageable);
}