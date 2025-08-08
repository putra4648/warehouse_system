package id.putra.wms.module.warehouse.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RackReq {

    private String id;

    private String name;

    private String location;

    private Integer total;

    private Long row;

    private Long col;

    private List<LocationReq> locations = new ArrayList<>();

}
