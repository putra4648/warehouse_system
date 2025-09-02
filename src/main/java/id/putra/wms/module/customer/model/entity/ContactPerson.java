package id.putra.wms.module.customer.model.entity;

import java.io.Serializable;

import id.putra.wms.module.inventory.model.entity.Supplier;
import id.putra.wms.module.warehouse.model.entity.Warehouse;
import id.putra.wms.shared.base.entity.BaseEntity;
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
class ContactPerson extends BaseEntity implements Serializable {
    @Id
    private String id;

    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

}
