package io.github.censodev.vrms.vrmsserver.data.models;

import io.github.censodev.vrms.vrmsserver.utils.enums.GenderEnum;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "patient_profile")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PatientProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String idCard;
    private String fullName;
    private Instant birthday;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private MstCountry country;

    @ManyToOne
    @JoinColumn(name = "nation_id")
    private MstNation nation;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private MstProvince province;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private MstDistrict district;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private MstWard ward;

    private String address;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Account createdBy;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
