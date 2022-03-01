package io.github.censodev.vrms.vrmsserver.http.models.profile;

import io.github.censodev.vrms.vrmsserver.http.models.SearchReq;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VcnProfileSearchReq extends SearchReq {
    private Long patientProfileId;
}
