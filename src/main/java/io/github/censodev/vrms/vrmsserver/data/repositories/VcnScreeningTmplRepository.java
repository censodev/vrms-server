package io.github.censodev.vrms.vrmsserver.data.repositories;

import io.github.censodev.vrms.vrmsserver.data.domains.VcnScreeningTmpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VcnScreeningTmplRepository extends JpaRepository<VcnScreeningTmpl, Long> {
    @Query("select s from VcnScreeningTmpl s where s.title like :kw")
    Page<VcnScreeningTmpl> search(@Param("kw") String keyword, Pageable pageable);
}