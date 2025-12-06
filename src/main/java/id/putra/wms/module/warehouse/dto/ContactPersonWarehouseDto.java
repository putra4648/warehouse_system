package id.putra.wms.module.warehouse.dto;

import java.util.List;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ContactPersonWarehouseDto extends BaseDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private List<WarehouseDto> warehouses;
}
