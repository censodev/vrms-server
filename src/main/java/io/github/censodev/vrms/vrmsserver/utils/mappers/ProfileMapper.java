package io.github.censodev.vrms.vrmsserver.utils.mappers;

import io.github.censodev.vrms.vrmsserver.data.models.*;
import io.github.censodev.vrms.vrmsserver.http.models.profile.PatientProfileCreateReq;
import io.github.censodev.vrms.vrmsserver.http.models.profile.PatientProfileRes;
import io.github.censodev.vrms.vrmsserver.http.models.profile.VcnProfileCreateReq;
import io.github.censodev.vrms.vrmsserver.http.models.profile.VcnProfileRes;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import io.github.censodev.vrms.vrmsserver.utils.enums.VcnProfileStatusEnum;

public class ProfileMapper {
    public static PatientProfile map(PatientProfileCreateReq model) {
        return PatientProfile.builder()
                .idCard(model.getIdCard())
                .fullName(model.getFullName())
                .birthday(model.getBirthday())
                .gender(model.getGender())
                .status(StatusEnum.ACTIVE)
                .country(MstCountry.builder().id(model.getCountryId()).build())
                .nation(MstNation.builder().id(model.getNationId()).build())
                .province(MstProvince.builder().id(model.getProvinceId()).build())
                .district(MstDistrict.builder().id(model.getDistrictId()).build())
                .ward(MstWard.builder().id(model.getWardId()).build())
                .build();
    }

    public static PatientProfileRes map(PatientProfile model) {
        return PatientProfileRes.builder()
                .id(model.getId())
                .idCard(model.getIdCard())
                .fullName(model.getFullName())
                .birthday(model.getBirthday())
                .gender(model.getGender())
                .status(model.getStatus())
                .countryId(model.getCountry().getId())
                .nationId(model.getNation().getId())
                .provinceId(model.getProvince().getId())
                .districtId(model.getDistrict().getId())
                .wardId(model.getWard().getId())
                .build();
    }

    public static VcnProfileRes map(VcnProfile model) {
        return VcnProfileRes.builder()
                .id(model.getId())
                .expectedInjectionTime(model.getExpectedInjectionTime())
                .patientProfileId(model.getPatientProfile().getId())
                .selectedPackageId(model.getSelectedPackage().getId())
                .status(model.getStatus())
                .selectedSiteId(model.getSelectedSite().getId())
                .build();
    }

    public static VcnProfile map(VcnProfileCreateReq req) {
        return VcnProfile.builder()
                .patientProfile(PatientProfile.builder().id(req.getPatientProfileId()).build())
                .expectedInjectionTime(req.getExpectedInjectionTime())
                .selectedPackage(VcnPackage.builder().id(req.getSelectedPackageId()).build())
                .selectedSite(VcnSite.builder().id(req.getSelectedSiteId()).build())
                .status(VcnProfileStatusEnum.CREATED)
                .build();
    }
}
