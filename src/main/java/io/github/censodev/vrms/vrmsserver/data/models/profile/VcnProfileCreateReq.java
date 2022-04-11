package io.github.censodev.vrms.vrmsserver.data.models.profile;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class VcnProfileCreateReq {
    private Instant expectedInjectionTime;
    private Long patientProfileId;
    private Long selectedPackageId;
    private Long selectedSiteId;
}
