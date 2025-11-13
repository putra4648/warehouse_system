package id.putra.wms.module.outbound.model.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Packing {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String packageNumber;
  @Column(precision = 12, scale = 2)
  private BigDecimal weight;
  private String dimension;

  @OneToMany(mappedBy = "packing")
  private List<Shipment> shipments;
}
