package io.github.censodev.vrms.vrmsserver.data.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OTPCreateReq {
    private String phone;
}
