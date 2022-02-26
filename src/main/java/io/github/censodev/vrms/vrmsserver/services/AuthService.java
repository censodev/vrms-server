package io.github.censodev.vrms.vrmsserver.services;

import com.censodev.jauthutils.jwt.TokenProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.censodev.vrms.vrmsserver.data.models.Account;
import io.github.censodev.vrms.vrmsserver.data.repositories.AccountRepository;
import io.github.censodev.vrms.vrmsserver.http.models.account.AccountCreateReq;
import io.github.censodev.vrms.vrmsserver.http.models.auth.LoginRes;
import io.github.censodev.vrms.vrmsserver.http.models.auth.LoginUsnPwdReq;
import io.github.censodev.vrms.vrmsserver.http.models.auth.LoginViaPhoneReq;
import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import io.github.censodev.vrms.vrmsserver.utils.RandomUtil;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MailService mailService;

    public AuthService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, MailService mailService) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.mailService = mailService;
    }

    public LoginRes login(LoginUsnPwdReq req) {
        return accountRepository.findByUsernameAndStatus(req.getUsername(), StatusEnum.ACTIVE)
                .filter(acc -> passwordEncoder.matches(req.getPassword(), acc.getPassword()))
                .map(acc -> {
                    try {
                        return LoginRes.builder()
                                .token(tokenProvider.generateToken(acc.toBuilder().password("").build()))
                                .expires(tokenProvider.getExpiration())
                                .build();
                    } catch (JsonProcessingException e) {
                        log.error(e.getMessage());
                        return null;
                    }
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, I18nUtil.get("auth.login.fail")));
    }

    public LoginRes login(LoginViaPhoneReq req) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
