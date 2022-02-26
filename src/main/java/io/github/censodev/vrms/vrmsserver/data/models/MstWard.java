package io.github.censodev.vrms.vrmsserver.data.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mst_ward")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MstWard {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "`rank`", length = 100)
    private String rank;

    @Column(name = "code", length = 10)
    private String code;

    @Column(name = "origin_id")
    private Integer originId;

    @Column(name = "short_name", length = 100)
    private String shortName;

    @Column(name = "signless_name", length = 100)
    private String signlessName;

    @Column(name = "province_id")
    private Integer provinceId;
}