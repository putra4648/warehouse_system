package id.putra.wms.module.inventory.model.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Table(name = "inventory_item")
@Entity
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class InventoryItem extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reservedQty;

    private String lotNumber;

    private ZonedDateTime expiredDate;

    private String status;

    private String serialNumber;

    private Long qty;

    @ManyToOne
    @JoinColumn(name = "product_sku", nullable = false)
    private Product product;

}
