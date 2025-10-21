package id.putra.wms.module.customer.model.entity;

import java.io.Serializable;

import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "customers")
@Data
@EqualsAndHashCode(callSuper = false)
public class Customer extends BaseEntity implements Serializable {
    @Id
    @Column(name = "customer_id")
    private String id;
    private String code;
    private String name;
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "contact_person_id")
    private ContactPersonCustomer contactPerson;
}
