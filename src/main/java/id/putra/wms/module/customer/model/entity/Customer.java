package id.putra.wms.module.customer.model.entity;

import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

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
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "contact_person_id")
    private ContactPersonCustomer contactPerson;
}
