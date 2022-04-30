package io.github.censodev.vrms.vrmsserver.utils.mappers;

import io.github.censodev.vrms.vrmsserver.data.domains.*;
import io.github.censodev.vrms.vrmsserver.data.models.profile.PatientProfileCreateReq;
import io.github.censodev.vrms.vrmsserver.data.models.profile.PatientProfileRes;
import io.github.censodev.vrms.vrmsserver.data.models.profile.VcnProfileCreateReq;
import io.github.censodev.vrms.vrmsserver.data.models.profile.VcnProfileRes;
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
                .address(model.getAddress())
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
                .country(model.getCountry())
                .nationId(model.getNation().getId())
                .nation(model.getNation())
                .provinceId(model.getProvince().getId())
                .province(model.getProvince())
                .districtId(model.getDistrict().getId())
                .district(model.getDistrict())
                .wardId(model.getWard().getId())
                .ward(model.getWard())
                .address(model.getAddress())
                .build();
    }

    public static VcnProfileRes map(VcnProfile model) {
        return VcnProfileRes.builder()
                .id(model.getId())
                .expectedInjectionTime(model.getExpectedInjectionTime())
                .injectionTime(model.getInjectionTime())
                .patientProfileId(model.getPatientProfile().getId())
                .patientProfile(map(model.getPatientProfile()))
                .selectedPackageId(model.getSelectedPackage().getId())
                .selectedPackage(VcnPackageMapper.map(model.getSelectedPackage()))
                .status(model.getStatus())
                .selectedSiteId(model.getSelectedSite().getId())
                .selectedSite(VcnSiteMapper.map(model.getSelectedSite()))
                .symptoms(model.getSymptoms())
                .screeningTestResult(model.getScreeningTestResult())
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
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
