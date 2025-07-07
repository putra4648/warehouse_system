package id.putra.wms.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Rack extends BaseEntity implements Serializable {
    @Id
    private String id;

    private String code;

    private Long rows;

    private Long cols;

    private Boolean isActive;

    @OneToMany(mappedBy = "rack")
    private List<Location> locations;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;
}
