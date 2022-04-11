package io.github.censodev.vrms.vrmsserver.data.domains;

import io.github.censodev.vrms.vrmsserver.utils.enums.VcnProfileStatusEnum;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "vcn_profile")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Account createdBy;
}
