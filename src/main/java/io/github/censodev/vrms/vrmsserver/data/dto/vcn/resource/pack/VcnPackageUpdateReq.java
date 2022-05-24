package io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.pack;

import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VcnPackageUpdateReq extends VcnPackageCreateReq {
    private Long id;
    private StatusEnum status;
}
