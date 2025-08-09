package id.putra.wms.module.warehouse.service.adapter.command;

import id.putra.wms.module.warehouse.dto.request.WarehouseReq;

public interface WarehouseCommandAdapter {

    void save(WarehouseReq req);

}
