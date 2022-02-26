package io.github.censodev.vrms.vrmsserver.http.models.vcn.resource.screening;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VcnScreeningTmplCreateReq {
    private String title;
    private Map<Object, Object> data;
}
