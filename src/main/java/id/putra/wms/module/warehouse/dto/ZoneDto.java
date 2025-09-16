package id.putra.wms.module.warehouse.dto;

import java.util.List;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class ZoneDto extends BaseDto {
        private String id;
        private String code;
        private String name;
        private Boolean isActive;
        private String type;
        private WarehouseDto warehouse;
        private List<RackDto> racks;
}
