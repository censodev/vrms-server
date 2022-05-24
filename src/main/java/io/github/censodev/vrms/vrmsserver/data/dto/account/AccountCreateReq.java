package io.github.censodev.vrms.vrmsserver.data.dto.account;

import io.github.censodev.vrms.vrmsserver.utils.enums.RoleEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateReq {
    private String username;
    private String email;
    private RoleEnum role;
}
