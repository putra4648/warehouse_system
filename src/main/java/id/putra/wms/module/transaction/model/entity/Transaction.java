package id.putra.wms.module.transaction.model.entity;

import id.putra.wms.module.customer.model.entity.Customer;
import id.putra.wms.module.inventory.model.entity.Supplier;
import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "transactions")
@Data
@EqualsAndHashCode(callSuper = false)
public class Transaction extends BaseEntity implements Serializable {
    @Id
    @Column(name = "transaction_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String txnNumber;
    private String txnType;
    private ZonedDateTime txnDate;
    private String sourceLocId;
    private String destLocId;
    private String refNumber;
    private String refType;
    private String status;
    private String notes;
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<TransactionItem> items;
}
