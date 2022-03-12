package io.github.censodev.vrms.vrmsserver.http.models.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OTPCreateReq {
    private String phone;
}
