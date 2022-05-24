package io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.screening;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VcnScreeningTmplCreateReq {
    private String title;
    private List<Object> data;
}
