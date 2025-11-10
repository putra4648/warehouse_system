package id.putra.wms.module.customer.model.entity;

import id.putra.wms.shared.base.entity.BaseEntity;
import id.putra.wms.shared.base.entity.Customer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "contact_person_customer")
@Data
@EqualsAndHashCode(callSuper = false)
public class ContactPersonCustomer extends BaseEntity implements Serializable {
    @Id
    @Column(name = "contact_person_id")
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "contactPerson")
    private List<Customer> customers;
}
