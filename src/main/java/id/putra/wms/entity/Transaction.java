package id.putra.wms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "transaction")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Transaction extends BaseEntity implements Serializable {
    @Id
    private String id;

    private String txnNumber;

    private String type;

    @Temporal(TemporalType.DATE)
    private Date date;

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
