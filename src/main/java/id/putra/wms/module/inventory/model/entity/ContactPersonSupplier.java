package id.putra.wms.module.inventory.model.entity;

import java.io.Serializable;

import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "contact_persons")
@Data
@EqualsAndHashCode(callSuper = false)
public class ContactPersonSupplier extends BaseEntity implements Serializable {
    @Id
    private String id;
    private String name;
    private String email;
    private String address;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "contact_person_id")
    private Supplier supplier;
}
