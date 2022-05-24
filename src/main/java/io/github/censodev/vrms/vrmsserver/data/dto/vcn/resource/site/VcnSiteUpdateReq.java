package io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.site;

import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VcnSiteUpdateReq extends VcnSiteCreateReq {
    private Long id;
    private StatusEnum status;
}
