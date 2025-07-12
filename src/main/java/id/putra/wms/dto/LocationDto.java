package id.putra.wms.dto;

import lombok.Data;

@Data
public class LocationDto {
    private String id;
    private String name;
    private String type;
    private Boolean is_active;
    private String bin_number;
}
