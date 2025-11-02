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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/master/warehouse")
@Tag(name = "Warehouse", description = "Warehouse management APIs")
@SecurityRequirement(name = "oauth2")
public class WarehouseController {
        private final ResponseHelper responseHelper;
        private final WarehouseCoreService warehouseCoreService;

        @PostMapping
        @Operation(summary = "Add new warehouses", description = "Create one or more new warehouse records")
        public ResponseEntity<ResponseData<String>> addWarehouse(@RequestBody @Valid List<WarehouseDto> body) {
                warehouseCoreService.save(body);
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
        }

        @PatchMapping
        @Operation(summary = "Update warehouses", description = "Update one or more existing warehouse records")
        public ResponseEntity<ResponseData<String>> updateWarehouse(@RequestBody @Valid List<WarehouseDto> body) {
                warehouseCoreService.update(body);
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
        }

        @DeleteMapping
        @Operation(summary = "Delete warehouses", description = "Delete one or more warehouse records by IDs")
        public ResponseEntity<ResponseData<String>> deleteWarehouse(
                        @Parameter(description = "List of warehouse IDs to delete") @RequestParam @Valid List<String> id) {
                warehouseCoreService.delete(id.stream().map(i -> WarehouseDto.builder().id(i).build()).toList());
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
        }

        @GetMapping
        @Operation(summary = "Get warehouses", description = "Retrieve a paginated list of warehouses with search functionality")
        public ResponseEntity<ResponseMeta<WarehouseDto>> getWarehouses(
                        @Parameter(description = "Search term for warehouse name") @RequestParam("search") String search,
                        @Parameter(description = "Pagination parameters") @PageableDefault Pageable pageable) {
                var dto = WarehouseDto.builder().name(search).build();
                Page<WarehouseDto> warehouse = warehouseCoreService.getWarehouses(dto, pageable);
                return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, warehouse);
        }

        @GetMapping("/{id}")
        @Operation(summary = "Get warehouse details", description = "Retrieve detailed information about a specific warehouse")
        public ResponseEntity<ResponseData<WarehouseDto>> getDetailWarehouse(
                        @Parameter(description = "Warehouse ID") @PathVariable Optional<String> id) {
                var dto = WarehouseDto.builder()
                                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                                .build();
                WarehouseDto wh = warehouseCoreService.getWarehouse(dto);
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, wh);
        }

}
