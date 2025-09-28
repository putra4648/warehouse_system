package id.putra.wms.module.inventory.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Embeddable
@Data
public class ProductSupplierId implements Serializable {
    @Column(name = "product_id")
    private String productId;

    @Column(name = "supplier_id")
    private String supplierId;
}
