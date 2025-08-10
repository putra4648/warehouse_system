package id.putra.wms.module.warehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.putra.wms.module.warehouse.dto.form.WarehouseForm;
import id.putra.wms.module.warehouse.dto.response.WarehouseRes;
import id.putra.wms.module.warehouse.service.core.WarehouseCoreService;
import id.putra.wms.shared.base.dto.response.ResponseData;
import id.putra.wms.shared.base.dto.response.ResponseMeta;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.helpers.ResponseHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/master-data/warehouse")
public class WarehouseController {

    private final ResponseHelper responseHelper;
    private final WarehouseCoreService warehouseCoreService;

    /**
     *
     * @param pageable default size page 10, size 0
     */
    @GetMapping
    public ResponseEntity<ResponseMeta<WarehouseRes>> getAll(
            @PageableDefault Pageable pageable) {
        Page<WarehouseRes> warehouses = warehouseCoreService.getAll(pageable);
        return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, warehouses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<WarehouseRes>> getById(@PathVariable String id) {
        WarehouseRes warehouse = warehouseCoreService.getById(id);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, warehouse);
    }

    @PostMapping
    public ResponseEntity<ResponseData<Object>> save(@Valid @RequestBody WarehouseForm req) {
        warehouseCoreService.save(req);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, null);
    }

}
