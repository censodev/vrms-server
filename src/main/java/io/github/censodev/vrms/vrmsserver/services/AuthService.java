package io.github.censodev.vrms.vrmsserver.services;

import com.censodev.jauthutils.jwt.TokenProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.censodev.vrms.vrmsserver.data.models.Account;
import io.github.censodev.vrms.vrmsserver.data.repositories.AccountRepository;
import io.github.censodev.vrms.vrmsserver.http.models.auth.LoginRes;
import io.github.censodev.vrms.vrmsserver.http.models.auth.LoginUsnPwdReq;
import io.github.censodev.vrms.vrmsserver.http.models.auth.LoginViaPhoneReq;
import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import io.github.censodev.vrms.vrmsserver.utils.enums.RoleEnum;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.SnsException;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;

@Service
@Slf4j
public class AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final SnsClient snsClient;

    public AuthService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, SnsClient snsClient) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.snsClient = snsClient;
    }

    public LoginRes login(LoginUsnPwdReq req, boolean forAdmin) {
        return accountRepository.findByUsernameAndStatus(req.getUsername(), StatusEnum.ACTIVE)
                .filter(acc -> passwordEncoder.matches(req.getPassword(), acc.getPassword()))
                .filter(acc -> !forAdmin || !acc.getRole().equals(RoleEnum.ROLE_GUEST))
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

    public void createOTPLoginSession(String phone) {
        var user = accountRepository.findByPhone(phone)
                .orElse(new Account());
        if (user.getStatus().equals(StatusEnum.IN_ACTIVE)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, I18nUtil.get("auth.login.inactive"));
        }
        try {
            var otp = new DecimalFormat("000000").format(SecureRandom.getInstanceStrong().nextInt(999999));
            user.setPhone(phone);
            user.setOtp(otp);
            accountRepository.save(user);
            sendSMS(phone, otp);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
    }

    public LoginRes login(LoginViaPhoneReq req, boolean forAdmin) {
        return accountRepository.findByPhoneAndStatus(req.getPhone(), StatusEnum.ACTIVE)
                .filter(acc -> acc.getOtp().equals(req.getOtp()))
                .filter(acc -> !forAdmin || !acc.getRole().equals(RoleEnum.ROLE_GUEST))
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, I18nUtil.get("auth.login.fail-otp")));
    }

    private void sendSMS(String phone, String message) {
        try {
            var request = PublishRequest.builder()
                    .message(message)
                    .phoneNumber(phone)
                    .build();
            var result = snsClient.publish(request);
            log.info(result.messageId() + " Message sent. Status was " + result.sdkHttpResponse().statusCode());
        } catch (SnsException e) {
            log.error(e.awsErrorDetails().errorMessage());
        }
    }
}
