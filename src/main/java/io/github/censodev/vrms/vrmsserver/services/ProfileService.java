package io.github.censodev.vrms.vrmsserver.services;

import io.github.censodev.vrms.vrmsserver.data.domains.*;
import io.github.censodev.vrms.vrmsserver.data.models.profile.*;
import io.github.censodev.vrms.vrmsserver.data.repositories.PatientProfileRepository;
import io.github.censodev.vrms.vrmsserver.data.repositories.VcnProfileHistoryRepository;
import io.github.censodev.vrms.vrmsserver.data.repositories.VcnProfileRepository;
import io.github.censodev.vrms.vrmsserver.data.models.PageReq;
import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import io.github.censodev.vrms.vrmsserver.utils.SessionUtil;
import io.github.censodev.vrms.vrmsserver.utils.enums.VcnProfileStatusEnum;
import io.github.censodev.vrms.vrmsserver.utils.mappers.ProfileMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

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
        model.setCreatedBy(SessionUtil.getAuth().orElseThrow());
        patientProfileRepository.save(model);
    }

    public void updatePatientProfile(PatientProfileUpdateReq req) {
        var model = patientProfileRepository.findById(req.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, I18nUtil.get("profile.patient.not-found")));
        if (!model.getCreatedBy().getId().equals(SessionUtil.getAuth().map(Account::getId).orElseThrow())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        var dupIdCardOne = patientProfileRepository.findByIdCard(req.getIdCard());
        if (dupIdCardOne.isPresent() && !dupIdCardOne.get().getId().equals(model.getId())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, I18nUtil.get("profile.patient.id-card-exist"));
        }
        model.setIdCard(req.getIdCard());
        model.setFullName(req.getFullName());
        model.setBirthday(req.getBirthday());
        model.setGender(req.getGender());
        model.setCountry(MstCountry.builder().id(req.getCountryId()).build());
        model.setNation(MstNation.builder().id(req.getNationId()).build());
        model.setProvince(MstProvince.builder().id(req.getProvinceId()).build());
        model.setDistrict(MstDistrict.builder().id(req.getDistrictId()).build());
        model.setWard(MstWard.builder().id(req.getWardId()).build());
        patientProfileRepository.save(model);
    }

    public Page<PatientProfileRes> searchPatientProfile(PatientProfileSearchReq searchReq, PageReq pageReq) {
        return patientProfileRepository
                .search("%" + searchReq.getKeyword() + "%", pageReq.toPageable())
                .map(ProfileMapper::map);
    }

    public Page<PatientProfileRes> getMyPatientProfile(PageReq pageReq) {
        return patientProfileRepository
                .findByCreatedById(SessionUtil.getAuth().map(Account::getId).orElseThrow(), pageReq.toPageable())
                .map(ProfileMapper::map);
    }

    public void createVcnProfile(VcnProfileCreateReq req) {
        var model = ProfileMapper.map(req);
        model.setCreatedBy(SessionUtil.getAuth().orElseThrow());
        model = vcnProfileRepository.save(model);
        vcnProfileHistoryRepository.save(VcnProfileHistory.builder()
                .createdBy(SessionUtil.getAuth().orElseThrow())
                .vcnProfile(model)
                .time(Instant.now())
                .status(VcnProfileStatusEnum.CREATED)
                .build());
    }

    public Page<VcnProfileRes> searchVcnProfiles(VcnProfileSearchReq searchReq, PageReq pageReq) {
        return vcnProfileRepository
                .findByPatientProfileId(searchReq.getPatientProfileId(), pageReq.toPageable())
                .map(ProfileMapper::map);
    }

    public Page<VcnProfileRes> getMyVcnProfiles(PageReq pageReq) {
        return vcnProfileRepository
                .findByCreatedById(SessionUtil.getAuth().map(Account::getId).orElseThrow(), pageReq.toPageable())
                .map(ProfileMapper::map);
    }
}
