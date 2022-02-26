package io.github.censodev.vrms.vrmsserver.data.models;

import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "patient_profile_privilege")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PatientProfilePrivilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "patient_profile_id")
    private PatientProfile patientProfile;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
