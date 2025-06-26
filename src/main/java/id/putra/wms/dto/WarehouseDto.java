package id.putra.wms.dto;

import id.putra.wms.entity.Location;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WarehouseDto {

    @NotBlank(message = "Warehouse CODE cannot be empty")
    private String id;

    @NotBlank(message = "Warehouse NAME cannot be empty")
    private String name;

    private Boolean status = true;

    private Location location;

    // Json formatted as string because its table data
    private String zone_json_string;

}
