package id.putra.wms.module.warehouse.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContactPersonWarehouseDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private List<WarehouseDto> warehouses;
}
