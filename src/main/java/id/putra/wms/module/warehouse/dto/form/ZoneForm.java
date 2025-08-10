package id.putra.wms.module.warehouse.dto.form;

import java.util.ArrayList;
import java.util.List;

import id.putra.wms.shared.base.dto.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ZoneForm extends BaseForm {
    private String id;
    private String name;
    private String type;
    private Double area;
    private Integer total;
    private List<RackForm> racks = new ArrayList<>();
}
