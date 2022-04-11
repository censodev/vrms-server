package io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.screening;

import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VcnScreeningTmplUpdateReq extends VcnScreeningTmplCreateReq {
    private Long id;
    private StatusEnum status;
}
