package id.putra.wms.shared.exceptions;

import id.putra.wms.config.exception.ModuleException;
import id.putra.wms.shared.enums.ResponseEnum;

public class DataNotFoundException extends ModuleException {

    public DataNotFoundException() {
        super(ResponseEnum.DATA_NOT_FOUND);
    }
}
