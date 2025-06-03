package id.putra.wms.dto;

import java.util.Date;

import lombok.Data;

@Data
public class InventoryDto {
    private String name;
    private Date date;
    private Date received_date;
    private String ref;
    private String supplier;
    private String status;
}