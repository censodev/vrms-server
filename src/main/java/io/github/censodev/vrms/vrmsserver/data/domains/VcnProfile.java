package io.github.censodev.vrms.vrmsserver.data.domains;

import com.vladmihalcea.hibernate.type.json.JsonType;
import io.github.censodev.vrms.vrmsserver.utils.enums.VcnProfileStatusEnum;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "vcn_profile")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "json", typeClass = JsonType.class)
public class VcnProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Instant expectedInjectionTime;
    private Instant injectionTime;

    @Enumerated(EnumType.STRING)
    private VcnProfileStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "patient_profile_id")
    private PatientProfile patientProfile;

    @ManyToOne
    @JoinColumn(name = "selected_package_id")
    private VcnPackage selectedPackage;

    @ManyToOne
    @JoinColumn(name = "selected_site_id")
    private VcnSite selectedSite;

    private String symptoms;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Object> screeningTestResult;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Account createdBy;
}
