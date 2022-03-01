package io.github.censodev.vrms.vrmsserver.http.controllers;

import io.github.censodev.vrms.vrmsserver.http.models.Res;
import io.github.censodev.vrms.vrmsserver.http.models.account.AccountCreateReq;
import io.github.censodev.vrms.vrmsserver.http.models.auth.LoginRes;
import io.github.censodev.vrms.vrmsserver.http.models.auth.LoginUsnPwdReq;
import io.github.censodev.vrms.vrmsserver.http.models.auth.LoginViaPhoneReq;
import io.github.censodev.vrms.vrmsserver.services.AuthService;
import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import io.github.censodev.vrms.vrmsserver.utils.enums.RoleEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<Res<LoginRes>> adminLogin(@RequestBody LoginUsnPwdReq req) {
        return ResponseEntity.ok(new Res<>(authService.login(req, true), I18nUtil.get("auth.login.success")));
    }

    @PostMapping("login")
    public ResponseEntity<Res<LoginRes>> login(@RequestBody LoginUsnPwdReq req) {
        return ResponseEntity.ok(new Res<>(authService.login(req, false), I18nUtil.get("auth.login.success")));
    }
}
