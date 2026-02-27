package id.putra.wms.module.supplier.controller;

import java.util.List;
import java.util.Optional;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.putra.wms.module.supplier.dto.SupplierDto;
import id.putra.wms.module.supplier.service.core.SupplierService;
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
@RequestMapping("/api/v1/master/supplier")
@Tag(name = "Supplier", description = "Supplier management APIs")
@SecurityRequirement(name = "oauth2")
public class SupplierController {
    private final ResponseHelper responseHelper;
    private final SupplierService supplierService;

    @PostMapping
    @Operation(summary = "Add new supplier", description = "Create a new supplier record")
    public ResponseEntity<ResponseData<List<SupplierDto>>> addSupplier(@RequestBody @Valid List<SupplierDto> body) {
        List<SupplierDto> createdSupplier = supplierService.create(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, createdSupplier);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Update supplier", description = "Update an existing supplier record")
    public ResponseEntity<ResponseData<SupplierDto>> updateSupplier(
            @Parameter(description = "Supplier ID") @PathVariable Long id,
            @RequestBody @Valid SupplierDto body) {
        SupplierDto updatedSupplier = supplierService.update(id, body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, updatedSupplier);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete supplier", description = "Delete a supplier record by ID")
    public ResponseEntity<ResponseData<String>> deleteSupplier(
            @Parameter(description = "Supplier ID to delete") @PathVariable Long id) {
        supplierService.delete(id);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @GetMapping
    @Operation(summary = "Get suppliers", description = "Retrieve a paginated list of suppliers with search functionality")

    public ResponseEntity<ResponseMeta<SupplierDto>> getSuppliers(
            @Parameter(description = "Search term for supplier name") @RequestParam(value = "search", defaultValue = "") String search,
            @ParameterObject @PageableDefault Pageable pageable) {
        Page<SupplierDto> suppliers = supplierService.getAll(pageable, search);
        return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, suppliers);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get supplier details", description = "Retrieve detailed information about a specific supplier")
    public ResponseEntity<ResponseData<SupplierDto>> getDetailSupplier(
            @Parameter(description = "Supplier ID") @PathVariable Optional<Long> id) {
        Long supplierId = id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM));
        SupplierDto supplier = supplierService.getById(supplierId);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, supplier);
    }
}
