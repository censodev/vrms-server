package io.github.censodev.vrms.vrmsserver.data.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mst_nation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MstNation {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;
}