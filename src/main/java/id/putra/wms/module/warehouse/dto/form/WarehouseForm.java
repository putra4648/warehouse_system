package id.putra.wms.module.warehouse.dto.form;

import java.util.ArrayList;
import java.util.List;

import id.putra.wms.shared.base.dto.form.BaseForm;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WarehouseForm extends BaseForm {
    @NotBlank(message = "Warehouse CODE cannot be empty")
    private String id;
    @NotBlank(message = "Warehouse NAME cannot be empty")
    private String name;
    private Boolean status = true;
    private String location;
    private Double area;
    private Integer total;
    private List<ZoneForm> zones = new ArrayList<>();
    // Json formatted as string because its table data
    private String zone_json_string;

}
