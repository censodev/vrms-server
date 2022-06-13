package io.github.censodev.vrms.vrmsserver.data.repositories;

import io.github.censodev.vrms.vrmsserver.data.domains.Account;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    Optional<Account> findByUsernameAndStatus(String username, StatusEnum status);

    Optional<Account> findByPhoneAndStatus(String phone, StatusEnum status);

    Optional<Account> findByPhone(String phone);
}