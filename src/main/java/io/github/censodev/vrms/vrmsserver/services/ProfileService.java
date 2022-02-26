package io.github.censodev.vrms.vrmsserver.services;

import io.github.censodev.vrms.vrmsserver.data.repositories.PatientProfileRepository;
import io.github.censodev.vrms.vrmsserver.data.repositories.VcnProfileHistoryRepository;
import io.github.censodev.vrms.vrmsserver.data.repositories.VcnProfileRepository;
import io.github.censodev.vrms.vrmsserver.http.models.PageReq;
import io.github.censodev.vrms.vrmsserver.http.models.profile.*;
import io.github.censodev.vrms.vrmsserver.http.models.vcn.process.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final PatientProfileRepository patientProfileRepository;
    private final VcnProfileRepository vcnProfileRepository;
    private final VcnProfileHistoryRepository vcnProfileHistoryRepository;

    public ProfileService(PatientProfileRepository patientProfileRepository,
                          VcnProfileRepository vcnProfileRepository,
                          VcnProfileHistoryRepository vcnProfileHistoryRepository) {
        this.patientProfileRepository = patientProfileRepository;
        this.vcnProfileRepository = vcnProfileRepository;
        this.vcnProfileHistoryRepository = vcnProfileHistoryRepository;
    }

    public void createPatientProfile(PatientProfileCreateReq req) {}

    public void updatePatientProfile(PatientProfileUpdateReq req) {}

    public Page<PatientProfileRes> searchPatientProfile(PatientProfileSearchReq req, PageReq pageReq) {
        return null;
    }

    public void createVcnProfile(VcnProcessCreateReq req) {}

    public Page<VcnProfileRes> searchVcnProfiles(VcnProfileSearchReq searchReq, PageReq pageReq) {
        return null;
    }
}
