package id.putra.wms.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Location extends BaseEntity implements Serializable {
    @Id
    private String id;

    private String name;

    private String type;

    private String binNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id")
    private Rack rack;

}
