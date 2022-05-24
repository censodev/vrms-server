package io.github.censodev.vrms.vrmsserver.services;

import io.github.censodev.vrms.vrmsserver.data.models.stats.StatsReq;
import io.github.censodev.vrms.vrmsserver.data.models.stats.VcnProfileStats;
import io.github.censodev.vrms.vrmsserver.utils.enums.VcnProfileStatusEnum;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unchecked")
public class StatsService {
    @PersistenceContext
    private EntityManager em;

    public List<VcnProfileStats> statsVcnProfile(StatsReq req) {
        var sql = "select count(id), status\n" +
                "from vcn_profile\n" +
                "where updated_at between :startTime and :endTime\n" +
                "group by status";
        var query = em.createNativeQuery(sql);
        query.setParameter("startTime", req.getStartTime());
        query.setParameter("endTime", req.getEndTime());
        return ((List<Object[]>) query.getResultList())
                .stream()
                .map(tuple -> VcnProfileStats.builder()
                        .count((Integer) tuple[0])
                        .status(VcnProfileStatusEnum.valueOf((String) tuple[1]))
                        .build())
                .collect(Collectors.toList());
    }
}
