package io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.site;

import io.github.censodev.vrms.vrmsserver.data.domains.MstDistrict;
import io.github.censodev.vrms.vrmsserver.data.domains.MstProvince;
import io.github.censodev.vrms.vrmsserver.data.domains.MstWard;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VcnSiteRes {
    private Long id;
    private String name;
    private String address;
    private StatusEnum status;
    private Integer provinceId;
    private MstProvince province;
    private Integer districtId;
    private MstDistrict district;
    private Integer wardId;
    private MstWard ward;
}
