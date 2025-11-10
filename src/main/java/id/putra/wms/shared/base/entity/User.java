package id.putra.wms.shared.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class User {
    @Id
    @Column(nullable = false)
    private String email;
    private String username;
    private String name;
}
