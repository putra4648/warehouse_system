package id.putra.wms.module.transaction.model.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import id.putra.wms.module.customer.model.entity.Customer;
import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Table(name = "transaction")
@Entity
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class Transaction extends BaseEntity implements Serializable {

    @Id
    private String id;

    private String txnNumber;

    private String type;

    private ZonedDateTime date;

    private String refType;

    private String refNumber;

    private String status;

    private String notes;

    private Long totalAmount;

    @OneToMany(mappedBy = "transaction")
    private List<TransactionItem> transactionItems;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
