package id.putra.wms.module.warehouse.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ZoneReq {
    private String id;
    private String name;
    private String type;
    private Double area;
    private Integer total;
    private List<RackReq> racks = new ArrayList<>();
}
