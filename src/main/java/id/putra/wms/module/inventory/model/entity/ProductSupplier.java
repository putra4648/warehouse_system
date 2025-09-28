package id.putra.wms.module.inventory.model.entity;

import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "product_supplier")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductSupplier extends BaseEntity {

    @EmbeddedId
    private ProductSupplierId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("supplierId")
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    // This join table could be extended with additional columns,
    // such as the price the supplier charges for the product.
    // private Double price;
}
