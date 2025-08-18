package id.putra.wms.module.inventory.dto.form;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class InventoryForm {

    private Long id;

    private String name;

    private ZonedDateTime date;

    private ZonedDateTime receivedDate;

    private String reference;

    private String supplier;

    private String status;

}