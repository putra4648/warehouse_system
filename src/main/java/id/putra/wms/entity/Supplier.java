package id.putra.wms.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Supplier extends BaseEntity implements Serializable {
    @Id
    private String id;

    private String code;

    private String name;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "product_sku", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "supplier")
    private List<ContactPerson> contactPersons;
}
