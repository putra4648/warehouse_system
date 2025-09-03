package id.putra.wms.module.warehouse.model.entity;

import java.io.Serializable;
import java.util.List;

import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "contact_persons")
@Data
@EqualsAndHashCode(callSuper = false)
public class ContactPersonWarehouse extends BaseEntity implements Serializable {
    @Id
    private String id;
    private String name;
    private String email;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "contactPersonWarehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Warehouse> warehouses;
}
