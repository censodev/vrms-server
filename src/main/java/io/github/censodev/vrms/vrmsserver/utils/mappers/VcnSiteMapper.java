package io.github.censodev.vrms.vrmsserver.utils.mappers;

import io.github.censodev.vrms.vrmsserver.data.domains.MstDistrict;
import io.github.censodev.vrms.vrmsserver.data.domains.MstProvince;
import io.github.censodev.vrms.vrmsserver.data.domains.MstWard;
import io.github.censodev.vrms.vrmsserver.data.domains.VcnSite;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.site.VcnSiteCreateReq;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.site.VcnSiteRes;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;

public class VcnSiteMapper {
    public static VcnSiteRes map(VcnSite model) {
        return VcnSiteRes.builder()
                .id(model.getId())
                .name(model.getName())
                .address(model.getAddress())
                .status(model.getStatus())
                .provinceId(model.getProvince().getId())
                .province(model.getProvince())
                .districtId(model.getDistrict().getId())
                .district(model.getDistrict())
                .wardId(model.getWard().getId())
                .ward(model.getWard())
                .build();
    }

    public static VcnSite map(VcnSiteCreateReq req) {
        return VcnSite.builder()
                .name(req.getName())
                .address(req.getAddress())
                .status(StatusEnum.ACTIVE)
                .province(MstProvince.builder().id(req.getProvinceId()).build())
                .district(MstDistrict.builder().id(req.getDistrictId()).build())
                .ward(MstWard.builder().id(req.getWardId()).build())
                .build();
    }
}
