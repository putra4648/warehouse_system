package id.putra.wms.shared.base.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import id.putra.wms.module.supplier.model.entity.ContactPersonSupplier;
import id.putra.wms.module.supplier.model.entity.ProductSupplier;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSupplier> products;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactPersonSupplier> contactPersonSuppliers = new ArrayList<>();
}
