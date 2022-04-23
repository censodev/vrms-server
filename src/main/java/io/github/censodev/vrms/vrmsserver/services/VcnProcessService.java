package io.github.censodev.vrms.vrmsserver.services;

import io.github.censodev.vrms.vrmsserver.data.domains.VcnProfileHistory;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.process.*;
import io.github.censodev.vrms.vrmsserver.data.repositories.VcnProfileHistoryRepository;
import io.github.censodev.vrms.vrmsserver.data.repositories.VcnProfileRepository;
import io.github.censodev.vrms.vrmsserver.utils.SessionUtil;
import io.github.censodev.vrms.vrmsserver.utils.enums.VcnProfileStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
public class VcnProcessService {
    private final VcnProfileRepository vcnProfileRepository;
    private final VcnProfileHistoryRepository vcnProfileHistoryRepository;

    public VcnProcessService(VcnProfileRepository vcnProfileRepository,
                             VcnProfileHistoryRepository vcnProfileHistoryRepository) {
        this.vcnProfileRepository = vcnProfileRepository;
        this.vcnProfileHistoryRepository = vcnProfileHistoryRepository;
    }

    @Transactional
    public void processCheckIn(VcnProcessCheckInReq req) {
        var profile = vcnProfileRepository.findById(req.getVcnProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
        if (profile.getStatus() != VcnProfileStatusEnum.CREATED) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        profile.setStatus(VcnProfileStatusEnum.CHECKED_IN);
        vcnProfileHistoryRepository.save(VcnProfileHistory.builder()
                .createdBy(SessionUtil.getAuth().orElseThrow())
                .vcnProfile(profile)
                .time(Instant.now())
                .status(VcnProfileStatusEnum.CHECKED_IN)
                .build());
    }

    @Transactional
    public void processTest(VcnProcessTestReq req) {
        var profile = vcnProfileRepository.findById(req.getVcnProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));

        if (profile.getStatus() != VcnProfileStatusEnum.CHECKED_IN) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        profile.setScreeningTestResult(req.getData());
        var newStatus = req.getOk()
                ? VcnProfileStatusEnum.TESTED_PASSED
                : VcnProfileStatusEnum.TESTED_FAILED;
        profile.setStatus(newStatus);
        vcnProfileHistoryRepository.save(VcnProfileHistory.builder()
                .createdBy(SessionUtil.getAuth().orElseThrow())
                .vcnProfile(profile)
                .time(Instant.now())
                .status(newStatus)
                .build());
    }

    @Transactional
    public void processPayment(VcnProcessPaymentReq req) {
    }

    @Transactional
    public void processComplete(VcnProcessCompleteReq req) {
        var profile = vcnProfileRepository.findById(req.getVcnProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
        if (profile.getStatus() != VcnProfileStatusEnum.PAID) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        profile.setStatus(VcnProfileStatusEnum.COMPLETED);
        vcnProfileHistoryRepository.save(VcnProfileHistory.builder()
                .createdBy(SessionUtil.getAuth().orElseThrow())
                .vcnProfile(profile)
                .time(Instant.now())
                .status(VcnProfileStatusEnum.COMPLETED)
                .build());
    }

    @Transactional
    public void processFail(VcnProcessFailReq req) {
        var profile = vcnProfileRepository.findById(req.getVcnProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
        if (profile.getStatus() == VcnProfileStatusEnum.FAILED
                || profile.getStatus() == VcnProfileStatusEnum.TESTED_FAILED) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        profile.setStatus(VcnProfileStatusEnum.FAILED);
        profile.setSymptoms(req.getSymptoms());
        vcnProfileHistoryRepository.save(VcnProfileHistory.builder()
                .createdBy(SessionUtil.getAuth().orElseThrow())
                .vcnProfile(profile)
                .time(Instant.now())
                .status(VcnProfileStatusEnum.FAILED)
                .build());
    }

    @Transactional
    public void processCancel(VcnProcessCancelReq req) {
        var profile = vcnProfileRepository.findById(req.getVcnProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
        if (profile.getStatus() == VcnProfileStatusEnum.COMPLETED
                || profile.getStatus() == VcnProfileStatusEnum.CANCELED
                || profile.getStatus() == VcnProfileStatusEnum.FAILED
                || profile.getStatus() == VcnProfileStatusEnum.TESTED_FAILED) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        profile.setStatus(VcnProfileStatusEnum.CANCELED);
        vcnProfileHistoryRepository.save(VcnProfileHistory.builder()
                .createdBy(SessionUtil.getAuth().orElseThrow())
                .vcnProfile(profile)
                .time(Instant.now())
                .status(VcnProfileStatusEnum.CANCELED)
                .build());
    }
}
