package io.github.censodev.vrms.vrmsserver.data.models.stats;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class StatsReq {
    private Instant startTime;
    private Instant endTime;
}
