package io.github.censodev.vrms.vrmsserver.data.repositories;

import io.github.censodev.vrms.vrmsserver.data.domains.VcnSite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VcnSiteRepository extends JpaRepository<VcnSite, Long> {
    @Query("select s from VcnSite s where s.name like :kw")
    Page<VcnSite> search(@Param("kw") String keyword, Pageable pageable);

    Optional<VcnSite> searchByName(String name);
}