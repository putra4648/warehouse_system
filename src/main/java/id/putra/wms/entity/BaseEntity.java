package id.putra.wms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.Date;

@Document
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    @Field
    @CreatedDate
    private Date createdDate;

    @Field
    @CreatedBy
    private String createdBy;

    @Field
    @LastModifiedDate
    private Date updatedDate;

    @Field
    @LastModifiedBy
    private String updatedBy;
}
