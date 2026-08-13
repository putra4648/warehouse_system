package id.putra.wms.module.outbound.model.entity;

import java.sql.Date;

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
public class Shipment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String shipmentNumber;
  private Date shipDate;
  private String status;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "packing_id")
  private Packing packing;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "carrier_id")
  private Carrier carrier;
}
