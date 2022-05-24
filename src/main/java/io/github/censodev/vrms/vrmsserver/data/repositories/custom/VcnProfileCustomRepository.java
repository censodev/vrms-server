package io.github.censodev.vrms.vrmsserver.data.repositories.custom;

import io.github.censodev.vrms.vrmsserver.data.domains.VcnProfile;
import io.github.censodev.vrms.vrmsserver.data.dto.profile.VcnProfileSearchReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VcnProfileCustomRepository {
    Page<VcnProfile> search(VcnProfileSearchReq req, Pageable pageable);
}
