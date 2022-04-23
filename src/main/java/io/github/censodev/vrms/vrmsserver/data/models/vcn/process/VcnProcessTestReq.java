package io.github.censodev.vrms.vrmsserver.data.models.vcn.process;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VcnProcessTestReq {
    private Long vcnProfileId;
    private List<Object> data;
    private Boolean ok;
}
