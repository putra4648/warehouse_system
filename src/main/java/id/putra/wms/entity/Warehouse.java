package id.putra.wms.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Warehouse extends BaseEntity implements Serializable {

    @Id
    private String id;

    private String name;

    @Column(columnDefinition = "bool default true", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "warehouse")
    private List<Zone> zones;

    @OneToMany(mappedBy = "warehouse")
    private List<ContactPerson> contactPersons;

}
