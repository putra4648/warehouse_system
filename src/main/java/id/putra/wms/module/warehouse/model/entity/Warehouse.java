package id.putra.wms.module.warehouse.model.entity;

import java.io.Serializable;
import java.util.List;

import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Table(name = "warehouses")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class Warehouse extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean isActive;

    private String location;

    private Double area;

    private Integer total;

    @ToString.Exclude
    @OneToMany(mappedBy = "warehouse", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<Zone> zones;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "contact_person_id", referencedColumnName = "id")
    private ContactPersonWarehouse contactPersonWarehouse;

}
