package io.github.censodev.vrms.vrmsserver.data.dto.profile;

import io.github.censodev.vrms.vrmsserver.data.domains.*;
import io.github.censodev.vrms.vrmsserver.utils.enums.GenderEnum;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class PatientProfileRes {
    private Long id;
    private String idCard;
    private String fullName;
    private Instant birthday;
    private GenderEnum gender;
    private Integer countryId;
    private MstCountry country;
    private Integer nationId;
    private MstNation nation;
    private Integer provinceId;
    private MstProvince province;
    private Integer districtId;
    private MstDistrict district;
    private Integer wardId;
    private MstWard ward;
    private String address;
    private StatusEnum status;
    private boolean primary;
}
