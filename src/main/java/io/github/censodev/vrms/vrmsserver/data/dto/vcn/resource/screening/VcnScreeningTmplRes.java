package io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.screening;

import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.*;

import java.util.List;

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
