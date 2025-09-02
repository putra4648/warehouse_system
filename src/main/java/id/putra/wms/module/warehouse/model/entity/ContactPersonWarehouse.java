package id.putra.wms.module.warehouse.model.entity;

import java.io.Serializable;
import java.util.List;

import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "contact_persons")
@Builder(toBuilder = true)
@Getter
public class ContactPersonWarehouse extends BaseEntity implements Serializable {
    @Id
    private String id;
    private String name;
    private String email;
    private String address;
    private String phone;

    @OneToMany
    private List<Warehouse> warehouses;
}
