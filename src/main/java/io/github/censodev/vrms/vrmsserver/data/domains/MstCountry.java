package io.github.censodev.vrms.vrmsserver.data.domains;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mst_country")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MstCountry {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", length = 10)
    private String code;

    @Column(name = "name", length = 100)
    private String name;
}