package id.putra.wms.module.supplier.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductSupplierId implements Serializable {
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "supplier_id")
    private Long supplierId;
}
