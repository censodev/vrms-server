package io.github.censodev.vrms.vrmsserver.http.models.vcn.resource.screening;

import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VcnScreeningTmplRes {
    private Long id;
    private String title;
    private List<Object> data;
    private StatusEnum status;
}
