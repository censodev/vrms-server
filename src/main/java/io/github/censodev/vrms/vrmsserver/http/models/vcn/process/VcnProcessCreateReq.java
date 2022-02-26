package io.github.censodev.vrms.vrmsserver.http.models.vcn.process;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class VcnProcessCreateReq {
    private Instant expectedInjectionTime;
    private Long patientProfileId;
    private Long selectedPackageId;
    private Long selectedSiteId;
}
