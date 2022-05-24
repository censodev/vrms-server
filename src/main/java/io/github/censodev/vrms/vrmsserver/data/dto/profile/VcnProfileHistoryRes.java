package io.github.censodev.vrms.vrmsserver.data.dto.profile;

import io.github.censodev.vrms.vrmsserver.data.dto.account.AccountRes;
import io.github.censodev.vrms.vrmsserver.utils.enums.VcnProfileStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class VcnProfileHistoryRes {
    private Long id;
    private VcnProfileStatusEnum status;
    private Instant time;
    private AccountRes createdBy;
}
