package id.putra.wms.dto;

import java.util.List;

import lombok.Data;

@Data
public class ZoneDto {
    private String id;
    private String name;
    private String type;
    private List<RackDto> racks;
}
