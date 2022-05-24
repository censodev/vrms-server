package io.github.censodev.vrms.vrmsserver.utils.mappers;

import io.github.censodev.vrms.vrmsserver.data.domains.Account;
import io.github.censodev.vrms.vrmsserver.data.dto.account.AccountRes;

public class AccountMapper {
    private AccountMapper() {
    }

    public static AccountRes map(Account model) {
        return AccountRes.builder()
                .id(model.getId())
                .username(model.getUsername())
                .phone(model.getPhone())
                .email(model.getEmail())
                .role(model.getRole())
                .status(model.getStatus())
                .build();
    }
}
