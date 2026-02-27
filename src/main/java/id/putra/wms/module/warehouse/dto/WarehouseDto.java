package id.putra.wms.module.warehouse.dto;

import java.util.List;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class WarehouseDto extends BaseDto {
    private Long id;
    private String name;
    private Boolean isActive;
    private String location;
    private Double area;
    private Integer total;
    private List<ZoneDto> zones;
    private ContactPersonWarehouseDto contactPersonWarehouse;
}
