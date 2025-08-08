package id.putra.wms.module.warehouse.dto.request;

import lombok.Data;

@Data
public class LocationReq {

    private String id;

    private String name;

    private String type;

    private Boolean isActive;

    private String binNumber;

}
