package io.github.censodev.vrms.vrmsserver.utils;

import io.github.censodev.vrms.vrmsserver.data.models.Account;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SessionUtil {
    public static Optional<Account> getAuth() {
        return Optional.ofNullable((Account) SecurityContextHolder.getContext().getAuthentication().getCredentials());
    }
}
