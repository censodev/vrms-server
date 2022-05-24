package io.github.censodev.vrms.vrmsserver.data.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUsnPwdReq {
    private String username;
    private String password;
}
