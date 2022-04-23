package io.github.censodev.vrms.vrmsserver.data.models.profile;

import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.pack.VcnPackageRes;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.site.VcnSiteRes;
import io.github.censodev.vrms.vrmsserver.utils.enums.VcnProfileStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
public class VcnProfileRes {
    private Long id;
    private Instant expectedInjectionTime;
    private Instant injectionTime;
    private VcnProfileStatusEnum status;
    private Long patientProfileId;
    private PatientProfileRes patientProfile;
    private Long selectedPackageId;
    private VcnPackageRes selectedPackage;
    private Long selectedSiteId;
    private VcnSiteRes selectedSite;
    private String symptoms;
    private List<Object> screeningTestResult;
}
