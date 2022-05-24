package io.github.censodev.vrms.vrmsserver.data.dto.vcn.process;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VcnProcessFailReq {
    private Long vcnProfileId;
    private String symptoms;
}
