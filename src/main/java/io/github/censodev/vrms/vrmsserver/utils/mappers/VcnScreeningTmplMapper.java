package io.github.censodev.vrms.vrmsserver.utils.mappers;

import io.github.censodev.vrms.vrmsserver.data.domains.VcnScreeningTmpl;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.screening.VcnScreeningTmplCreateReq;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.screening.VcnScreeningTmplRes;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;

public class VcnScreeningTmplMapper {
    public static VcnScreeningTmpl map(VcnScreeningTmplCreateReq req) {
        return VcnScreeningTmpl.builder()
                .title(req.getTitle())
                .data(req.getData())
                .status(StatusEnum.ACTIVE)
                .build();
    }

    public static VcnScreeningTmplRes map(VcnScreeningTmpl model) {
        return VcnScreeningTmplRes.builder()
                .id(model.getId())
                .title(model.getTitle())
                .data(model.getData())
                .status(model.getStatus())
                .build();
    }
}
