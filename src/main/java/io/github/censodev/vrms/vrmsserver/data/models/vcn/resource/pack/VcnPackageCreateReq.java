package io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.pack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VcnPackageCreateReq {
    private String name;
    private String desc;
    private Double price;
    private String diseasesCode;
    private Long screeningTemplateId;
}
