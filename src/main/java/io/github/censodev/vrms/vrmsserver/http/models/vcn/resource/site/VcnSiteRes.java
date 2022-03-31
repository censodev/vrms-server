package io.github.censodev.vrms.vrmsserver.http.models.vcn.resource.site;

import io.github.censodev.vrms.vrmsserver.data.models.MstDistrict;
import io.github.censodev.vrms.vrmsserver.data.models.MstProvince;
import io.github.censodev.vrms.vrmsserver.data.models.MstWard;
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
