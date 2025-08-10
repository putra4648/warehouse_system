package id.putra.wms.module.warehouse.dto.form;

import id.putra.wms.shared.base.dto.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LocationReq extends BaseForm {

    private String id;

    private String name;

    private String type;

    private Boolean isActive;

    private String binNumber;

}
