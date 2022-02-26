package io.github.censodev.vrms.vrmsserver.http.models.vcn.resource.screening;

import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VcnScreeningTmplRes {
    private Long id;
    private String title;
    private Map<Object, Object> data;
    private StatusEnum status;
}
