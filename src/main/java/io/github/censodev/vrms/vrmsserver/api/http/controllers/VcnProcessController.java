package io.github.censodev.vrms.vrmsserver.api.http.controllers;

import io.github.censodev.vrms.vrmsserver.data.models.Res;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.process.*;
import io.github.censodev.vrms.vrmsserver.services.VcnProcessService;
import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import io.github.censodev.vrms.vrmsserver.utils.enums.RoleEnum;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vcn/process")
public class VcnProcessController {
    private final VcnProcessService vcnProcessService;

    public VcnProcessController(VcnProcessService vcnProcessService) {
        this.vcnProcessService = vcnProcessService;
    }

    @PostMapping("check-in")
    @Secured({RoleEnum.Const.ROLE_AGENT})
    public Res<Void> processCheckIn(@RequestBody VcnProcessCheckInReq req) {
        vcnProcessService.processCheckIn(req);
        return new Res<>(null, I18nUtil.get("vcn.process.checkin-success"));
    }

    @PostMapping("test")
    @Secured({RoleEnum.Const.ROLE_AGENT})
    public Res<Void> processScreeningTest(@RequestBody VcnProcessTestReq req) {
        vcnProcessService.processTest(req);
        return new Res<>(null, I18nUtil.get("vcn.process.test-success"));
    }

    @PostMapping("pay")
    public Res<Void> processScreeningTest(@RequestBody VcnProcessPaymentReq req) {
        vcnProcessService.processPayment(req);
        return new Res<>(null, I18nUtil.get("vcn.process.pay-success"));
    }

    @PostMapping("complete")
    @Secured({RoleEnum.Const.ROLE_AGENT})
    public Res<Void> processComplete(@RequestBody VcnProcessCompleteReq req) {
        vcnProcessService.processComplete(req);
        return new Res<>(null, I18nUtil.get("vcn.process.complete"));
    }

    @PostMapping("fail")
    @Secured({RoleEnum.Const.ROLE_AGENT})
    public Res<Void> processFail(@RequestBody VcnProcessFailReq req) {
        vcnProcessService.processFail(req);
        return new Res<>(null, I18nUtil.get("vcn.process.fail"));
    }

    @PostMapping("cancel")
    public Res<Void> processComplete(@RequestBody VcnProcessCancelReq req) {
        vcnProcessService.processCancel(req);
        return new Res<>(null, I18nUtil.get("vcn.process.cancel"));
    }
}
