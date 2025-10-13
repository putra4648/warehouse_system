package id.putra.wms.module.supplier.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ProductSupplierId implements Serializable {
    @Column(name = "product_id")
    private String productId;

    @Column(name = "supplier_id")
    private String supplierId;
}
