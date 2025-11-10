package id.putra.wms.module.inbound.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class ReturnToVendor {
      @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String reason;
    @Column(precision = 12, scale = 2)
    private BigDecimal qty;
    

    @ManyToOne
    @JoinColumn(name = "receiving_line_id")
    private ReceivingLine receivingLine;
}
