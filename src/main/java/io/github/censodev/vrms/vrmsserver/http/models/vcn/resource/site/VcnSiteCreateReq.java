package io.github.censodev.vrms.vrmsserver.http.models.vcn.resource.site;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VcnSiteCreateReq {
    private String name;
    private String address;
    private Integer provinceId;
    private Integer districtId;
    private Integer wardId;
}
