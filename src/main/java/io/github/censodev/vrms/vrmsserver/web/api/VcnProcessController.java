package io.github.censodev.vrms.vrmsserver.web.api;

import io.github.censodev.vrms.vrmsserver.data.dto.Res;
import io.github.censodev.vrms.vrmsserver.data.dto.vcn.process.*;
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
    @Secured({RoleEnum.Const.ROLE_AGENT, RoleEnum.Const.ROLE_AGENT_CHECKIN})
    public Res<Void> processCheckIn(@RequestBody VcnProcessCheckInReq req) {
        vcnProcessService.processCheckIn(req);
        return new Res<>(null, I18nUtil.get("vcn.process.checkin-success"));
    }

    @PostMapping("test")
    @Secured({RoleEnum.Const.ROLE_AGENT, RoleEnum.Const.ROLE_AGENT_TEST})
    public Res<Void> processScreeningTest(@RequestBody VcnProcessTestReq req) {
        vcnProcessService.processTest(req);
        return new Res<>(null, I18nUtil.get("vcn.process.test-success"));
    }

    @PostMapping("pay")
    @Secured({RoleEnum.Const.ROLE_AGENT, RoleEnum.Const.ROLE_AGENT_PAY})
    public Res<Void> processScreeningTest(@RequestBody VcnProcessPaymentReq req) {
        vcnProcessService.processPayment(req);
        return new Res<>(null, I18nUtil.get("vcn.process.pay-success"));
    }

    @PostMapping("inject")
    @Secured({RoleEnum.Const.ROLE_AGENT, RoleEnum.Const.ROLE_AGENT_INJECT})
    public Res<Void> processInjection(@RequestBody VcnProcessInjectionReq req) {
        vcnProcessService.processInjection(req);
        return new Res<>(null, I18nUtil.get("vcn.process.injection-success"));
    }

    @PostMapping("complete")
    @Secured({RoleEnum.Const.ROLE_AGENT, RoleEnum.Const.ROLE_AGENT_MONITOR})
    public Res<Void> processComplete(@RequestBody VcnProcessCompleteReq req) {
        vcnProcessService.processComplete(req);
        return new Res<>(null, I18nUtil.get("vcn.process.complete"));
    }

    @PostMapping("fail")
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
