package id.putra.wms.module.warehouse.model.entity;

import java.io.Serializable;

import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Table(name = "warehouse_locations")
@Entity
@Getter
@SuperBuilder
public class Location extends BaseEntity implements Serializable {
    @Id
    private String id;

    private String name;

    private String type;

    private String binNumber;

    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id")
    private Rack rack;

}
