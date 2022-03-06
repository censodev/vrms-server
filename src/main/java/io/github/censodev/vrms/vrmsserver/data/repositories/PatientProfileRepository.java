package io.github.censodev.vrms.vrmsserver.data.repositories;

import io.github.censodev.vrms.vrmsserver.data.models.PatientProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long> {
    @Query("select p from PatientProfile p where p.fullName like :kw or p.idCard like :kw")
    Page<PatientProfile> search(@Param("kw") String keyword, Pageable pageable);

    Optional<PatientProfile> findByIdCard(String idCard);

    Page<PatientProfile> findByCreatedById(Long createdById, Pageable pageable);
}