package io.github.censodev.vrms.vrmsserver.data.seeders;

import io.github.censodev.vrms.vrmsserver.data.repositories.AccountRepository;
import io.github.censodev.vrms.vrmsserver.data.dto.account.AccountCreateReq;
import io.github.censodev.vrms.vrmsserver.services.AccountService;
import io.github.censodev.vrms.vrmsserver.utils.BeanUtil;
import io.github.censodev.vrms.vrmsserver.utils.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountSeeder implements Runnable {
    @Value("${seeder.account.username}")
    private String username;
    @Value("${seeder.account.email}")
    private String email;

    @Override
    public void run() {
        var repo = BeanUtil.getBean(AccountRepository.class);
        if (repo.findByUsername(username).isPresent())
            return;
        var service = BeanUtil.getBean(AccountService.class);
        service.create(AccountCreateReq.builder()
                .username(username)
                .email(email)
                .role(RoleEnum.ROLE_ADMIN)
                .build());
    }
}
