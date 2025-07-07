package id.putra.wms.dto;

import java.util.List;

import lombok.Data;

@Data
public class RackDto {
    private String rack_id;
    private String rack_name;
    private Long rack_rows;
    private Long rack_cols;
    private List<LocationDto> rack_locations;
}
