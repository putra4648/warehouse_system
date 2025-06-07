package id.putra.wms.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Location extends BaseEntity implements Serializable {
    @Id
    private String id;

    private String code;

    private String name;

    private String type;

    private Boolean isActive;

    private String binNumber;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "rack_id", nullable = false)
    private Rack rack;

    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;

}
