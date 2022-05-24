package io.github.censodev.vrms.vrmsserver.data.dto.account;

import io.github.censodev.vrms.vrmsserver.utils.enums.RoleEnum;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateReq {
    private Long id;
    private String email;
    private RoleEnum role;
    private StatusEnum status;
}
