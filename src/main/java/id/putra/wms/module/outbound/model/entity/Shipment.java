package id.putra.wms.module.outbound.model.entity;

import java.sql.Date;

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
public class Shipment {
      @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String shipmentNumber;
    private Date shipDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "packing_id")
    private Packing packing;


    @ManyToOne
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;
}
