package id.putra.wms.module.inventory.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
}
