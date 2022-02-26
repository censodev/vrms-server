package io.github.censodev.vrms.vrmsserver.services;

import io.github.censodev.vrms.vrmsserver.data.repositories.VcnPackageRepository;
import io.github.censodev.vrms.vrmsserver.data.repositories.VcnScreeningTmplRepository;
import io.github.censodev.vrms.vrmsserver.data.repositories.VcnSiteRepository;
import io.github.censodev.vrms.vrmsserver.http.models.vcn.process.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VcnProcessService {
    private final VcnSiteRepository vcnSiteRepository;
    private final VcnScreeningTmplRepository vcnScreeningTmplRepository;
    private final VcnPackageRepository vcnPackageRepository;

    public VcnProcessService(VcnSiteRepository vcnSiteRepository,
                              VcnScreeningTmplRepository vcnScreeningTmplRepository,
                              VcnPackageRepository vcnPackageRepository) {
        this.vcnSiteRepository = vcnSiteRepository;
        this.vcnScreeningTmplRepository = vcnScreeningTmplRepository;
        this.vcnPackageRepository = vcnPackageRepository;
    }

    @Transactional
    public void processCheckIn(VcnProcessCheckInReq req) {}

    @Transactional
    public void processTest(VcnProcessTestReq req) {}

    @Transactional
    public void processPayment(VcnProcessPaymentReq req) {}

    @Transactional
    public void processComplete(VcnProcessCompleteReq req) {}

    @Transactional
    public void processFail(VcnProcessFailReq req) {}

    @Transactional
    public void processCancel(VcnProcessCancelReq req) {}
}
