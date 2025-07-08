package id.putra.wms.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WarehouseDto {
    @NotBlank(message = "Warehouse CODE cannot be empty")
    private String id;
    @NotBlank(message = "Warehouse NAME cannot be empty")
    private String name;
    private Boolean status = true;
    private String location;
    private Double area;
    private Integer total;
    private List<ZoneDto> zones = new ArrayList<>();
    // Json formatted as string because its table data
    private String zone_json_string;

}
