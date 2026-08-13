package id.putra.wms.module.warehouse.dto;

import java.util.List;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
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
