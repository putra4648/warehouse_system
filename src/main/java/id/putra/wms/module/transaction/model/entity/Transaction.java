package id.putra.wms.module.transaction.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import id.putra.wms.shared.base.entity.BaseEntity;
import id.putra.wms.shared.base.entity.Customer;
import id.putra.wms.shared.base.entity.Supplier;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private Long quantity;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<TransactionItem> items;
}
