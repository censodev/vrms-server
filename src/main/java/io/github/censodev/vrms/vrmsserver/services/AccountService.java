package io.github.censodev.vrms.vrmsserver.services;

import io.github.censodev.vrms.vrmsserver.data.domains.Account;
import io.github.censodev.vrms.vrmsserver.data.dto.PageReq;
import io.github.censodev.vrms.vrmsserver.data.dto.account.AccountCreateReq;
import io.github.censodev.vrms.vrmsserver.data.dto.account.AccountRes;
import io.github.censodev.vrms.vrmsserver.data.dto.account.AccountSearchReq;
import io.github.censodev.vrms.vrmsserver.data.dto.account.AccountUpdateReq;
import io.github.censodev.vrms.vrmsserver.data.repositories.AccountRepository;
import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import io.github.censodev.vrms.vrmsserver.utils.RandomUtil;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import io.github.censodev.vrms.vrmsserver.utils.mappers.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AmazonWebService aws;

    public AccountService(AccountRepository accountRepository,
                          PasswordEncoder passwordEncoder,
                          AmazonWebService aws) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.aws = aws;
    }

    public void create(AccountCreateReq req) {
        if (accountRepository.findByUsername(req.getUsername()).isPresent())
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, I18nUtil.get("account.create.already-exist"));
        var pwd = RandomUtil.generatePassword(12);
        var model = Account.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(pwd))
                .email(req.getEmail())
                .role(req.getRole())
                .status(StatusEnum.ACTIVE)
                .build();
        accountRepository.save(model);
        aws.sendMail(req.getEmail(), "Thông tin đăng nhập", String.format("Tên đăng nhập: <b>%s</b><br/>Mật khẩu: <b>%s</b>", req.getUsername(), pwd));
        log.info(String.format("username / password: %s / %s", model.getUsername(), pwd));
    }

    public void update(AccountUpdateReq req) {
        var model = accountRepository.findById(req.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
        model.setEmail(req.getEmail());
        model.setRole(req.getRole());
        model.setStatus(req.getStatus());
        accountRepository.save(model);
    }

    public Page<AccountRes> search(AccountSearchReq searchReq, PageReq pageReq) {
        var matcher = ExampleMatcher.matchingAny()
                .withMatcher("email", contains())
                .withMatcher("username", contains())
                .withMatcher("phone", contains());
        var probe = Account.builder()
                .email(searchReq.getKeyword())
                .username(searchReq.getKeyword())
                .phone(searchReq.getKeyword())
                .build();
        return accountRepository.findAll(Example.of(probe, matcher), pageReq.toPageable())
                .map(AccountMapper::map);
    }

    public AccountRes getOne(Long id) {
        return accountRepository.findById(id)
                .map(AccountMapper::map)
                .orElseThrow();
    }
}
