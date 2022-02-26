package io.github.censodev.vrms.vrmsserver.http.controllers;

import io.github.censodev.vrms.vrmsserver.http.models.PageReq;
import io.github.censodev.vrms.vrmsserver.http.models.Res;
import io.github.censodev.vrms.vrmsserver.http.models.profile.*;
import io.github.censodev.vrms.vrmsserver.http.models.vcn.process.*;
import io.github.censodev.vrms.vrmsserver.services.ProfileService;
import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("patient")
    public Res<Page<PatientProfileRes>> searchPatientProfiles(PatientProfileSearchReq searchReq, PageReq pageReq) {
        return new Res<>(profileService.searchPatientProfile(searchReq, pageReq), "");
    }

    @PostMapping("patient")
    public Res<Void> createPatientProfile(@RequestBody PatientProfileCreateReq req) {
        profileService.createPatientProfile(req);
        return new Res<>(null, I18nUtil.get("profile.patient.create-success"));
    }

    @PutMapping("patient")
    public Res<Void> updatePatientProfile(@RequestBody PatientProfileUpdateReq req) {
        profileService.updatePatientProfile(req);
        return new Res<>(null, I18nUtil.get("profile.patient.update-success"));
    }

    @GetMapping("vcn")
    public Res<Page<VcnProfileRes>> searchVcnProfiles(VcnProfileSearchReq searchReq, PageReq pageReq) {
        return new Res<>(profileService.searchVcnProfiles(searchReq, pageReq), "");
    }

    @PostMapping("vcn")
    public Res<Void> createVcnProfile(@RequestBody VcnProcessCreateReq req) {
        profileService.createVcnProfile(req);
        return new Res<>(null, I18nUtil.get("profile.vcn.create-success"));
    }
}
