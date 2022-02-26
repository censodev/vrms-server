package io.github.censodev.vrms.vrmsserver.http.models.profile;

import io.github.censodev.vrms.vrmsserver.utils.enums.VcnProfileStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class VcnProfileRes {
    private Long id;
    private Instant expectedInjectionTime;
    private VcnProfileStatusEnum status;
    private Long patientProfileId;
    private Long selectedPackageId;
    private Long selectedSiteId;
}
