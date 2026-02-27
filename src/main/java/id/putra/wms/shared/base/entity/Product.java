package id.putra.wms.shared.base.entity;

import java.io.Serializable;
import java.util.List;

import id.putra.wms.module.inventory.model.entity.Category;
import id.putra.wms.module.inventory.model.entity.InventoryItem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "product")
@Data
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private String name;
    private String description;
    private Long minStock;
    private Long maxStock;
    private Boolean isActive;
    private Long quantity;

    @OneToMany(mappedBy = "product")
    private List<InventoryItem> inventories;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
