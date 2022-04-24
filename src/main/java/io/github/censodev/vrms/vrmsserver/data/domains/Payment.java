package io.github.censodev.vrms.vrmsserver.data.domains;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "payment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vcn_profile_id")
    private VcnProfile vcnProfile;

    private Double amount;

    private Instant createdAt;
}