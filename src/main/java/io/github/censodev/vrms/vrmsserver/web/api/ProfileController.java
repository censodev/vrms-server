package io.github.censodev.vrms.vrmsserver.web.api;

import io.github.censodev.vrms.vrmsserver.data.dto.PageReq;
import io.github.censodev.vrms.vrmsserver.data.dto.Res;
import io.github.censodev.vrms.vrmsserver.data.dto.profile.*;
import io.github.censodev.vrms.vrmsserver.services.ProfileService;
import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("patient/me")
    public Res<Page<PatientProfileRes>> getMyPatientProfiles(PageReq pageReq) {
        return new Res<>(profileService.getMyPatientProfile(pageReq), "");
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

    @GetMapping("vcn/me")
    public Res<Page<VcnProfileRes>> getMyVcnProfiles(PageReq pageReq) {
        return new Res<>(profileService.getMyVcnProfiles(pageReq), "");
    }

    @GetMapping("vcn/{id}")
    public Res<VcnProfileRes> getVcnProfile(@PathVariable Long id) {
        return new Res<>(profileService.getVcnProfile(id), "");
    }

    @GetMapping("vcn/{id}/history")
    public Res<List<VcnProfileHistoryRes>> getVcnProfileHistories(@PathVariable Long id) {
        return new Res<>(profileService.getVcnProfileHistories(id), "");
    }

    @GetMapping("vcn/{id}/payment")
    public Res<List<VcnProfilePaymentRes>> getVcnProfilePayments(@PathVariable Long id) {
        return new Res<>(profileService.getVcnProfilePayments(id), "");
    }

    @PostMapping("vcn")
    public Res<Void> createVcnProfile(@RequestBody VcnProfileCreateReq req) {
        profileService.createVcnProfile(req);
        return new Res<>(null, I18nUtil.get("profile.vcn.create-success"));
    }
}
