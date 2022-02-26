package io.github.censodev.vrms.vrmsserver.utils.mappers;

import io.github.censodev.vrms.vrmsserver.data.models.Account;
import io.github.censodev.vrms.vrmsserver.http.models.account.AccountRes;

public class AccountMapper {
    public static AccountRes map(Account model) {
        return AccountRes.builder()
                .id(model.getId())
                .username(model.getUsername())
                .email(model.getEmail())
                .role(model.getRole())
                .status(model.getStatus())
                .build();
    }
}
