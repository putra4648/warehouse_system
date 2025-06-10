package id.putra.wms.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductDto {
    @NotBlank(message = "Product SKU cannot be empty")
    private String sku;

    @NotBlank(message = "Product name cannot be empty")
    private String name;

    private String description;

    @NotNull(message = "Minimum stock cannot be empty")
    @Min(value = 0)
    @Positive
    private Long min_stock;

    @NotNull(message = "Maximum stock cannot be empty")
    @Min(value = 1)
    @Positive
    private Long max_stock;

    private Boolean is_active = true;

    private String category_id;
}
