package id.putra.wms.dto;

import java.util.List;

import lombok.Data;

@Data
public class ZoneDto {
    private String zone_id;
    private String zone_name;
    private String zone_type;
    private List<RackDto> zone_racks;
}
