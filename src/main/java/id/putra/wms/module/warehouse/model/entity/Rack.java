package id.putra.wms.module.warehouse.model.entity;

import java.io.Serializable;
import java.util.List;

import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Rack extends BaseEntity implements Serializable {
    @Id
    private String id;

    private String name;

    private Long rows;

    private Long cols;

    private Boolean isActive;

    @OneToMany(mappedBy = "rack")
    private List<Location> locations;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;
}
