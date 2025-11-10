package id.putra.wms.module.inbound.model.entity;

import java.sql.Date;
import java.util.List;

import id.putra.wms.shared.base.entity.Supplier;
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
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String poNumber;
    private String status;
    private Date orderDate;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<PurchaseOrderLine> purchaseOrderLines;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<Receiving> receivings;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
