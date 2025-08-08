package id.putra.wms.module.warehouse.dto.response;

import java.util.List;

public record WarehouseRes(
        String id,
        String name,
        Boolean isActive,
        String location,
        Double area,
        Integer total,
        List<ZoneRes> zones
) {
}
