package io.github.censodev.vrms.vrmsserver.data.dto.profile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class VcnProfilePaymentRes {
    private Long id;
    private Double amount;
    private Instant createdAt;
}
