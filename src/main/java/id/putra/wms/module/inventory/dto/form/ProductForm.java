package id.putra.wms.module.inventory.dto.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductForm {

    @NotBlank(message = "Product SKU cannot be empty")
    private String sku;

    @NotBlank(message = "Product name cannot be empty")
    private String name;

    private String description;

    @NotNull(message = "Minimum stock cannot be empty")
    @Min(value = 0)
    @Positive
    private Long minStock;

    @NotNull(message = "Maximum stock cannot be empty")
    @Min(value = 1)
    @Positive
    private Long maxStock;

    private String status;

    private Boolean isActive = true;

    private String categoryId;

}
