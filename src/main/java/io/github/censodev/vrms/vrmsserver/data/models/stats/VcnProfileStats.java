package io.github.censodev.vrms.vrmsserver.data.models.stats;

import io.github.censodev.vrms.vrmsserver.utils.enums.VcnProfileStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class VcnProfileStats {
    private VcnProfileStatusEnum status;
    private Integer count;
}
