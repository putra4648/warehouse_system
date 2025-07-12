package id.putra.wms.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "inventory_item")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class InventoryItem extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reservedQty;

    private String lotNumber;

    @Temporal(TemporalType.DATE)
    private Date expiredDate;

    private String status;

    private String serialNumber;

    private Long qty;

    @ManyToOne
    @JoinColumn(name = "product_sku", nullable = false)
    private Product product;

}
