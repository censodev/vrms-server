package io.github.censodev.vrms.vrmsserver.data.repositories.custom.impl;

import io.github.censodev.vrms.vrmsserver.data.domains.VcnProfile;
import io.github.censodev.vrms.vrmsserver.data.dto.profile.VcnProfileSearchReq;
import io.github.censodev.vrms.vrmsserver.data.repositories.custom.VcnProfileCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class VcnProfileCustomRepositoryImpl implements VcnProfileCustomRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<VcnProfile> search(VcnProfileSearchReq req, Pageable pageable) {
        var jpql = "select vp from VcnProfile vp where 1 = 1\n";
        var countJpql = "select count(vp.id) from VcnProfile vp where 1 = 1\n";

        if (req.getPatientProfileId() != null) {
            jpql += "and vp.patientProfile.id = :patientProfileId\n";
            countJpql += "and vp.patientProfile.id = :patientProfileId\n";
        }
        if (req.getStatus() != null) {
            jpql += "and vp.status = :status\n";
            countJpql += "and vp.status = :status\n";
        }
        if (req.getKeyword() != null && !req.getKeyword().isBlank()) {
            jpql += "and (vp.patientProfile.idCard like :kw or vp.patientProfile.fullName like :kw or vp.patientProfile.birthday like :kw)\n";
            countJpql += "and (vp.patientProfile.idCard like :kw or vp.patientProfile.fullName like :kw or vp.patientProfile.birthday like :kw)\n";
        }

        var query = em.createQuery(jpql, VcnProfile.class)
                .setMaxResults(pageable.getPageSize())
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        var countQuery = em.createQuery(countJpql);

        if (req.getPatientProfileId() != null) {
            query.setParameter("patientProfileId", req.getPatientProfileId());
            countQuery.setParameter("patientProfileId", req.getPatientProfileId());
        }
        if (req.getStatus() != null) {
            query.setParameter("status", req.getStatus());
            countQuery.setParameter("status", req.getStatus());
        }
        if (req.getKeyword() != null && !req.getKeyword().isBlank()) {
            query.setParameter("kw", "%" + req.getKeyword() + "%");
            countQuery.setParameter("kw", "%" + req.getKeyword() + "%");
        }

        long total = (long) countQuery.getSingleResult();
        var content = query.getResultList();
        return new PageImpl<>(content, pageable, total);
    }
}
