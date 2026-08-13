package id.putra.wms.module.inbound.model.entity;

import java.math.BigDecimal;
import java.sql.Date;

import id.putra.wms.module.warehouse.model.entity.Location;
import id.putra.wms.shared.base.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceivingLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date receivedDate;
    private String status;
    private String lotBatch;
    private Date expiryDate;

    @Column(precision = 12, scale = 2)
    private BigDecimal qtyReceived;

    @Column(precision = 12, scale = 2, name = "ordered_qty")
    private BigDecimal qty;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "receiving_id")
    private Receiving receiving;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
