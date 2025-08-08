package id.putra.wms.module.inventory.dto.request;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class InventoryReq {

    private Long id;

    private String name;

    private ZonedDateTime date;

    private ZonedDateTime receivedDate;

    private String reference;

    private String supplier;

    private String status;

}