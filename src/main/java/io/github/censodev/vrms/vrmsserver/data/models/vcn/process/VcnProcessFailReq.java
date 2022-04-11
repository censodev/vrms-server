package io.github.censodev.vrms.vrmsserver.data.models.vcn.process;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VcnProcessFailReq {
    private Long vcnProfileId;
    private String symptoms;
}
