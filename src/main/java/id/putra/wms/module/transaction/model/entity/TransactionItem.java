package id.putra.wms.module.transaction.model.entity;

import id.putra.wms.module.inventory.model.entity.InventoryItem;
import id.putra.wms.module.inventory.model.entity.Product;
import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "transaction_items")
@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionItem extends BaseEntity implements Serializable {
    @Id
    @Column(name = "transaction_item_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "source_inv_item_id")
    private InventoryItem sourceInventoryItem;

    @ManyToOne
    @JoinColumn(name = "dest_inv_item_id")
    private InventoryItem destInventoryItem;

    private Integer qty;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String lotNumber;
    private String serialNumber;
    private String notes;
}
