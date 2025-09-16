package id.putra.wms.module.warehouse.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.service.core.WarehouseCoreService;
import id.putra.wms.shared.base.dto.response.ResponseData;
import id.putra.wms.shared.base.dto.response.ResponseMeta;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.exceptions.ModuleException;
import id.putra.wms.shared.helpers.ResponseHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/master-data/warehouse")
public class WarehouseController {
        private final ResponseHelper responseHelper;
        private final WarehouseCoreService warehouseCoreService;

        @PostMapping
        public ResponseEntity<ResponseData<String>> addWarehouse(@RequestBody @Valid List<WarehouseDto> body) {
                warehouseCoreService.save(body);
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
        }

        @PatchMapping
        public ResponseEntity<ResponseData<String>> updateWarehouse(@RequestBody @Valid List<WarehouseDto> body) {
                warehouseCoreService.update(body);
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
        }

        @DeleteMapping
        public ResponseEntity<ResponseData<String>> deleteWarehouse(@RequestParam @Valid List<String> id) {
                warehouseCoreService.delete(id.stream().map(i -> WarehouseDto.builder().id(i).build()).toList());
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
        }

        @GetMapping
        public ResponseEntity<ResponseMeta<WarehouseDto>> getWarehouses(@RequestParam("search") String search,
                        @PageableDefault Pageable pageable) {
                var dto = WarehouseDto.builder().name(search).build();
                Page<WarehouseDto> warehouse = warehouseCoreService.getWarehouses(dto, pageable);
                return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, warehouse);
        }

        @GetMapping("/{id}")
        public ResponseEntity<ResponseData<WarehouseDto>> getDetailWarehouse(@PathVariable Optional<String> id) {
                var dto = WarehouseDto.builder()
                                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                                .build();
                WarehouseDto wh = warehouseCoreService.getWarehouse(dto);
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, wh);
        }

}
