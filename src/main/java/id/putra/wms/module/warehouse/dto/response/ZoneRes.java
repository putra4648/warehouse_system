package id.putra.wms.module.warehouse.dto.response;

public record ZoneRes(
        String code,
        String name,
        Boolean isActive,
        String type
) {
}
