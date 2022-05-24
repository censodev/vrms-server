package io.github.censodev.vrms.vrmsserver.data.dto.profile;

import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientProfileUpdateReq extends PatientProfileCreateReq {
    private Long id;
    private StatusEnum status;
}
