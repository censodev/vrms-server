package io.github.censodev.vrms.vrmsserver.web.api;

import io.github.censodev.vrms.vrmsserver.data.dto.Res;
import io.github.censodev.vrms.vrmsserver.data.dto.auth.LoginRes;
import io.github.censodev.vrms.vrmsserver.data.dto.auth.LoginUsnPwdReq;
import io.github.censodev.vrms.vrmsserver.data.dto.auth.LoginViaPhoneReq;
import io.github.censodev.vrms.vrmsserver.data.dto.auth.OTPCreateReq;
import io.github.censodev.vrms.vrmsserver.services.AuthService;
import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("admin/login")
    public Res<LoginRes> adminLogin(@RequestBody LoginUsnPwdReq req) {
        return new Res<>(authService.login(req, true), I18nUtil.get("auth.login.success"));
    }

    @PostMapping("login")
    public Res<LoginRes> guestLogin(@RequestBody LoginViaPhoneReq req) {
        return new Res<>(authService.login(req, false), I18nUtil.get("auth.login.success"));
    }

    @PostMapping("otp")
    public Res<Void> getOTP(@RequestBody OTPCreateReq req) {
        authService.createOTPLoginSessionForGuest(req);
        return new Res<>(null, "");
    }
}
