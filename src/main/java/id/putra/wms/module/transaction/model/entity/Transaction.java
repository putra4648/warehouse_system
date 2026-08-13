package id.putra.wms.module.transaction.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import id.putra.wms.shared.base.entity.BaseEntity;
import id.putra.wms.shared.base.entity.Customer;
import id.putra.wms.shared.base.entity.Supplier;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class Transaction extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ToString.Exclude
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

    @ToString.Exclude
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<TransactionItem> items;
}
