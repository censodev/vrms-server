package io.github.censodev.vrms.vrmsserver.services;

import io.github.censodev.vrms.vrmsserver.data.repositories.PatientProfileRepository;
import io.github.censodev.vrms.vrmsserver.data.repositories.VcnProfileHistoryRepository;
import io.github.censodev.vrms.vrmsserver.data.repositories.VcnProfileRepository;
import io.github.censodev.vrms.vrmsserver.http.models.PageReq;
import io.github.censodev.vrms.vrmsserver.http.models.profile.*;
import io.github.censodev.vrms.vrmsserver.http.models.vcn.process.VcnProcessCreateReq;
import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import io.github.censodev.vrms.vrmsserver.utils.mappers.ProfileMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void createPatientProfile(PatientProfileCreateReq req) {
        if (patientProfileRepository.findByIdCard(req.getIdCard()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, I18nUtil.get("profile.patient.id-card-exist"));
        }
        var model = ProfileMapper.map(req);
        patientProfileRepository.save(model);
    }

    public void updatePatientProfile(PatientProfileUpdateReq req) {
    }

    public Page<PatientProfileRes> searchPatientProfile(PatientProfileSearchReq searchReq, PageReq pageReq) {
        return patientProfileRepository
                .search("%" + searchReq.getKeyword() + "%", pageReq.toPageable())
                .map(ProfileMapper::map);
    }

    public void createVcnProfile(VcnProcessCreateReq req) {
    }

    public Page<VcnProfileRes> searchVcnProfiles(VcnProfileSearchReq searchReq, PageReq pageReq) {
        return null;
    }
}
