package id.putra.wms.module.transaction.model.entity;

import java.io.Serializable;

import id.putra.wms.module.inventory.model.entity.Product;
import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "transaction_item")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionItem extends BaseEntity implements Serializable {
    @Id
    private String id;

    private Long qty;

    private Long price;

    private Long total;

    private String lotNumber;

    private String serialNumber;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_sku", nullable = false)
    private Product product;

}
