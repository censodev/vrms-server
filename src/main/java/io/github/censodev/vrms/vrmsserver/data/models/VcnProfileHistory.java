package io.github.censodev.vrms.vrmsserver.data.models;

import io.github.censodev.vrms.vrmsserver.utils.enums.VcnProfileStatusEnum;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "vcn_profile_history")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VcnProfileHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vcn_profile_id")
    private VcnProfile vcnProfile;

    @Enumerated(EnumType.STRING)
    private VcnProfileStatusEnum status;

    private Instant time;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Account createdBy;
}
