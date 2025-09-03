package id.putra.wms.module.customer.model.entity;

import java.io.Serializable;
import java.util.List;

import id.putra.wms.module.transaction.model.entity.Transaction;
import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Entity
@Table
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class Customer extends BaseEntity implements Serializable {
    @Id
    private String id;

    private String code;

    private String name;

    private Boolean isActive;

    @OneToMany(mappedBy = "customer")
    private List<ContactPerson> contactPersons;

    @OneToMany(mappedBy = "customer")
    private List<Transaction> transactions;

}
