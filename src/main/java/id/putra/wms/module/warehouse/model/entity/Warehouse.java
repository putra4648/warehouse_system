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

@Table(name = "warehouses")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Warehouse extends BaseEntity implements Serializable {

    @Id
    private String id;

    private String name;

    private Boolean isActive;

    private String location;

    private Double area;

    private Integer total;

    @OneToMany(mappedBy = "warehouse", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<Zone> zones;

    @ManyToOne
    @JoinColumn(name = "contact_person_id", referencedColumnName = "id")
    private ContactPersonWarehouse contactPersonWarehouse;

}
