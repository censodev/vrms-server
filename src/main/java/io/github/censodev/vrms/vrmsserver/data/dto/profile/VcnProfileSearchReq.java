package io.github.censodev.vrms.vrmsserver.data.dto.profile;

import io.github.censodev.vrms.vrmsserver.data.dto.SearchReq;
import io.github.censodev.vrms.vrmsserver.utils.enums.VcnProfileStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VcnProfileSearchReq extends SearchReq {
    private Long patientProfileId;
    private VcnProfileStatusEnum status;
}
