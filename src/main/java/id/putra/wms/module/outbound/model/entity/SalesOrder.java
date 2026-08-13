package id.putra.wms.module.outbound.model.entity;

import java.sql.Date;
import java.util.List;

import id.putra.wms.shared.base.entity.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class SalesOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String soNumber;
  private Date orderDate;
  private String status;

  @ToString.Exclude
  @OneToMany(mappedBy = "salesOrder")
  private List<SalesOrderLine> salesOrderLines;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
}
