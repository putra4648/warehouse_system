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
public class ZoneDto extends BaseDto {
        private Long id;
        private String name;
        private String code;
        private Boolean isActive;
        private String type;
        /** parent warehouse id to avoid circular nested DTOs */
        private String warehouseId;
        private List<RackDto> racks;
}
