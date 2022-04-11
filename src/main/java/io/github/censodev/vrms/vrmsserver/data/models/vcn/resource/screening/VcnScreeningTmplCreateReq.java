package io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.screening;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VcnScreeningTmplCreateReq {
    private String title;
    private List<Object> data;
}
