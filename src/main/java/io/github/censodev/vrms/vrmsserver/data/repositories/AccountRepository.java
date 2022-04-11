package io.github.censodev.vrms.vrmsserver.data.repositories;

import io.github.censodev.vrms.vrmsserver.data.domains.Account;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    Optional<Account> findByUsernameAndStatus(String username, StatusEnum status);

    Optional<Account> findByPhoneAndStatus(String phone, StatusEnum status);

    @Query("select a from Account a where a.username like :kw or a.email like :kw or a.phone like :kw")
    Page<Account> search(@Param("kw") String keyword, Pageable pageable);

    Optional<Account> findByPhone(String phone);
}