package io.github.censodev.vrms.vrmsserver.services;

import io.github.censodev.vrms.vrmsserver.data.domains.Payment;
import io.github.censodev.vrms.vrmsserver.data.domains.VcnProfileHistory;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.process.*;
import io.github.censodev.vrms.vrmsserver.data.repositories.PaymentRepository;
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
    private final PaymentRepository paymentRepository;

    public VcnProcessService(VcnProfileRepository vcnProfileRepository,
                             VcnProfileHistoryRepository vcnProfileHistoryRepository,
                             PaymentRepository paymentRepository) {
        this.vcnProfileRepository = vcnProfileRepository;
        this.vcnProfileHistoryRepository = vcnProfileHistoryRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public void processCheckIn(VcnProcessCheckInReq req) {
        var profile = vcnProfileRepository.findById(req.getVcnProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
        if (profile.getStatus() != VcnProfileStatusEnum.CREATED) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        profile.setStatus(VcnProfileStatusEnum.CHECKED_IN);
        var now = Instant.now();
        profile.setUpdatedAt(now);
        vcnProfileHistoryRepository.save(VcnProfileHistory.builder()
                .createdBy(SessionUtil.getAuth().orElseThrow())
                .vcnProfile(profile)
                .time(now)
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
        var newStatus = Boolean.TRUE.equals(req.getOk())
                ? VcnProfileStatusEnum.TESTED_PASSED
                : VcnProfileStatusEnum.TESTED_FAILED;
        profile.setStatus(newStatus);
        var now = Instant.now();
        profile.setUpdatedAt(now);
        vcnProfileHistoryRepository.save(VcnProfileHistory.builder()
                .createdBy(SessionUtil.getAuth().orElseThrow())
                .vcnProfile(profile)
                .time(now)
                .status(newStatus)
                .build());
    }

    @Transactional
    public void processPayment(VcnProcessPaymentReq req) {
        var profile = vcnProfileRepository
                .findById(req.getVcnProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));

        if (profile.getStatus() != VcnProfileStatusEnum.TESTED_PASSED) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        profile.setStatus(VcnProfileStatusEnum.PAID);
        var now = Instant.now();
        profile.setUpdatedAt(now);

        paymentRepository.save(Payment.builder()
                .vcnProfile(profile)
                .amount(req.getAmount())
                .createdAt(now)
                .build());

        vcnProfileHistoryRepository.save(VcnProfileHistory.builder()
                .createdBy(SessionUtil.getAuth().orElseThrow())
                .vcnProfile(profile)
                .time(now)
                .status(VcnProfileStatusEnum.PAID)
                .build());
    }

    @Transactional
    public void processInjection(VcnProcessInjectionReq req) {
        var profile = vcnProfileRepository.findById(req.getVcnProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));

        if (profile.getStatus() != VcnProfileStatusEnum.PAID) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        profile.setStatus(VcnProfileStatusEnum.INJECTED);
        profile.setInjectionTime(Instant.now());
        var now = Instant.now();
        profile.setUpdatedAt(now);

        vcnProfileHistoryRepository.save(VcnProfileHistory.builder()
                .createdBy(SessionUtil.getAuth().orElseThrow())
                .vcnProfile(profile)
                .time(now)
                .status(VcnProfileStatusEnum.INJECTED)
                .build());
    }

    @Transactional
    public void processComplete(VcnProcessCompleteReq req) {
        var profile = vcnProfileRepository.findById(req.getVcnProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
        if (profile.getStatus() != VcnProfileStatusEnum.INJECTED) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        profile.setStatus(VcnProfileStatusEnum.COMPLETED);
        var now = Instant.now();
        profile.setUpdatedAt(now);
        vcnProfileHistoryRepository.save(VcnProfileHistory.builder()
                .createdBy(SessionUtil.getAuth().orElseThrow())
                .vcnProfile(profile)
                .time(now)
                .status(VcnProfileStatusEnum.COMPLETED)
                .build());
    }

    @Transactional
    public void processFail(VcnProcessFailReq req) {
        var profile = vcnProfileRepository.findById(req.getVcnProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
        if (profile.getStatus() != VcnProfileStatusEnum.INJECTED) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        profile.setStatus(VcnProfileStatusEnum.FAILED);
        profile.setSymptoms(req.getSymptoms());
        var now = Instant.now();
        profile.setUpdatedAt(now);
        vcnProfileHistoryRepository.save(VcnProfileHistory.builder()
                .createdBy(SessionUtil.getAuth().orElseThrow())
                .vcnProfile(profile)
                .time(now)
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
        var now = Instant.now();
        profile.setUpdatedAt(now);
        vcnProfileHistoryRepository.save(VcnProfileHistory.builder()
                .createdBy(SessionUtil.getAuth().orElseThrow())
                .vcnProfile(profile)
                .time(now)
                .status(VcnProfileStatusEnum.CANCELED)
                .build());
    }
}
