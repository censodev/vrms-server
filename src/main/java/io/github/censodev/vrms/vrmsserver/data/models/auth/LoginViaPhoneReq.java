package io.github.censodev.vrms.vrmsserver.data.models.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginViaPhoneReq {
    private String phone;
    private String otp;
}
