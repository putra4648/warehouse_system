package id.putra.wms.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @CreatedDate
    private Date createdDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private Date updatedDate;

    @LastModifiedBy
    private String updatedBy;
}
