package io.github.censodev.vrms.vrmsserver.http.models.vcn.resource.site;

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
    private Integer districtId;
    private Integer wardId;
}
