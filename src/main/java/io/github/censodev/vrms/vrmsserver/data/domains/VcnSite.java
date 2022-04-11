package io.github.censodev.vrms.vrmsserver.data.domains;

import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vcn_site")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VcnSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String address;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private MstProvince province;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private MstDistrict district;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private MstWard ward;
}