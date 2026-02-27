package id.putra.wms.module.inbound.model.entity;

import java.sql.Date;
import java.util.List;

import id.putra.wms.shared.base.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Receiving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String receivingNumber;
    private Date receivedDate;
    private String status;

    @OneToMany(mappedBy = "receiving")
    private List<ReceivingLine> receivingLines;

    @ManyToOne
    @JoinColumn(name = "received_by_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;
}
