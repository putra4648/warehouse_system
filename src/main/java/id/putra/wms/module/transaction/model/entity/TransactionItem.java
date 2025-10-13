package id.putra.wms.module.transaction.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import id.putra.wms.module.inventory.model.entity.InventoryItem;
import id.putra.wms.shared.base.entity.BaseEntity;
import id.putra.wms.shared.base.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
