package id.putra.wms.dto;

import java.util.List;

import lombok.Data;

@Data
public class RackDto {
    private String id;
    private String name;
    private Long row;
    private Long col;
    private List<LocationDto> locations;
}
