package id.putra.wms.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = false)
public class Customer extends BaseEntity implements Serializable {
    @Id
    private String id;

    private String code;

    private String name;

    private Boolean isActive;

    @OneToMany(mappedBy = "customer")
    private List<ContactPerson> contactPersons;

}
