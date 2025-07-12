package id.putra.wms.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column(name = "sku", nullable = false)
    private String id;

    private String name;

    private String description;

    private Long minStock;

    private Long maxStock;

    private Boolean isActive;

    @OneToMany(mappedBy = "product")
    private List<InventoryItem> inventories;

    @OneToMany(mappedBy = "product")
    private List<Supplier> products;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
