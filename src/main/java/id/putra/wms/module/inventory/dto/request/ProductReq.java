package id.putra.wms.module.inventory.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductReq {

    @JsonProperty("sku")
    @NotBlank(message = "Product SKU cannot be empty")
    private String sku;

    @JsonProperty("name")
    @NotBlank(message = "Product name cannot be empty")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("min_stock")
    @NotNull(message = "Minimum stock cannot be empty")
    @Min(value = 0)
    @Positive
    private Long minStock;

    @JsonProperty("max_stock")
    @NotNull(message = "Maximum stock cannot be empty")
    @Min(value = 1)
    @Positive
    private Long maxStock;

    @JsonProperty("status")
    private String status;

    @JsonProperty("is_active")
    private Boolean isActive = true;

    @JsonProperty("category_id")
    private String categoryId;

}
