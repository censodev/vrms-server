package io.github.censodev.vrms.vrmsserver.data.models;

import com.vladmihalcea.hibernate.type.json.JsonType;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vcn_screening_tmpl")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(
        name = "json", typeClass = JsonType.class
)
public class VcnScreeningTmpl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Object> data;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
