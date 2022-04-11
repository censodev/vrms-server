package io.github.censodev.vrms.vrmsserver.data.domains;

import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vcn_package")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VcnPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @Column(name = "`desc`")
    @Lob
    private String desc;
    private Double price;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private String diseasesCode;

    @ManyToOne
    @JoinColumn(name = "screening_template_id")
    private VcnScreeningTmpl screeningTemplate;
}
