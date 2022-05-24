package io.github.censodev.vrms.vrmsserver.data.dto.account;

import io.github.censodev.vrms.vrmsserver.utils.enums.RoleEnum;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountRes {
    private Long id;
    private String username;
    private String phone;
    private String email;
    private RoleEnum role;
    private StatusEnum status;
}
