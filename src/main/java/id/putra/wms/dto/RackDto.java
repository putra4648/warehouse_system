package id.putra.wms.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RackDto {
    private String id;
    private String name;
    private String location;
    private Integer total;
    private Long row;
    private Long col;
    private List<LocationDto> locations = new ArrayList<>();
}
