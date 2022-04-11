package io.github.censodev.vrms.vrmsserver.utils.mappers;

import io.github.censodev.vrms.vrmsserver.data.domains.VcnPackage;
import io.github.censodev.vrms.vrmsserver.data.domains.VcnScreeningTmpl;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.pack.VcnPackageCreateReq;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.pack.VcnPackageRes;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;

public class VcnPackageMapper {
    public static VcnPackage map(VcnPackageCreateReq req) {
        return VcnPackage.builder()
                .name(req.getName())
                .status(StatusEnum.ACTIVE)
                .price(req.getPrice())
                .screeningTemplate(VcnScreeningTmpl.builder().id(req.getScreeningTemplateId()).build())
                .desc(req.getDesc())
                .diseasesCode(req.getDiseasesCode())
                .build();
    }

    public static VcnPackageRes map(VcnPackage model) {
        return VcnPackageRes.builder()
                .id(model.getId())
                .name(model.getName())
                .desc(model.getDesc())
                .price(model.getPrice())
                .diseasesCode(model.getDiseasesCode())
                .screeningTemplateId(model.getScreeningTemplate().getId())
                .status(model.getStatus())
                .build();
    }
}
