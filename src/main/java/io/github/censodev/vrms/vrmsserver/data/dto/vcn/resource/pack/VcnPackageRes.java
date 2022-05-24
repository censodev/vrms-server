package io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.pack;

import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VcnPackageRes {
    private Long id;
    private String name;
    private String desc;
    private Double price;
    private StatusEnum status;
    private String diseasesCode;
    private Long screeningTemplateId;
}
