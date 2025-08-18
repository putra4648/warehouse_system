package id.putra.wms.module.warehouse.dto.form;

import java.util.ArrayList;
import java.util.List;

import id.putra.wms.shared.base.dto.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RackForm extends BaseForm {
    private String id;

    private String name;

    private String location;

    private Integer total;

    private Long row;

    private Long col;

    private List<LocationReq> locations = new ArrayList<>();

}
