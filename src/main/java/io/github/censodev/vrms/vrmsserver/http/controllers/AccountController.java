package io.github.censodev.vrms.vrmsserver.http.controllers;

import io.github.censodev.vrms.vrmsserver.http.models.PageReq;
import io.github.censodev.vrms.vrmsserver.http.models.Res;
import io.github.censodev.vrms.vrmsserver.http.models.account.AccountCreateReq;
import io.github.censodev.vrms.vrmsserver.http.models.account.AccountRes;
import io.github.censodev.vrms.vrmsserver.http.models.account.AccountSearchReq;
import io.github.censodev.vrms.vrmsserver.http.models.account.AccountUpdateReq;
import io.github.censodev.vrms.vrmsserver.services.AccountService;
import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import io.github.censodev.vrms.vrmsserver.utils.enums.RoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    @Secured({RoleEnum.Const.ROLE_ADMIN})
    public Res<Page<AccountRes>> search(AccountSearchReq searchReq, PageReq pageReq) {
        return new Res<>(accountService.search(searchReq, pageReq), "");
    }

    @PostMapping("")
    @Secured({RoleEnum.Const.ROLE_ADMIN})
    public ResponseEntity<Res<Void>> create(@RequestBody AccountCreateReq req) {
        accountService.create(req);
        return ResponseEntity.ok(new Res<>(null, I18nUtil.get("account.create.success")));
    }

    @PutMapping("")
    @Secured({RoleEnum.Const.ROLE_ADMIN})
    public ResponseEntity<Res<Void>> update(@RequestBody AccountUpdateReq req) {
        accountService.update(req);
        return ResponseEntity.ok(new Res<>(null, I18nUtil.get("account.create.success")));
    }
}
