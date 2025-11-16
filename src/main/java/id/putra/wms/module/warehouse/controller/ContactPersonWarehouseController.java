package id.putra.wms.module.warehouse.controller;

import java.util.List;
import java.util.Optional;

import org.springdoc.core.annotations.ParameterObject;
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

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;
import id.putra.wms.module.warehouse.service.core.ContactPersonWarehouseCoreService;
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
@RequestMapping("/api/v1/master/contact-person-warehouse")
@Tag(name = "Contact Person Warehouse", description = "Warehouse contact person management APIs")
@SecurityRequirement(name = "oauth2")
public class ContactPersonWarehouseController {
    private final ResponseHelper responseHelper;
    private final ContactPersonWarehouseCoreService contactPersonWarehouseCoreService;

    @PostMapping
    @Operation(summary = "Add warehouse contact persons", description = "Create one or more new warehouse contact person records")
    public ResponseEntity<ResponseData<String>> addContactPersonWarehouse(
            @RequestBody @Valid List<ContactPersonWarehouseDto> body) {
        contactPersonWarehouseCoreService.add(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @PatchMapping
    @Operation(summary = "Update warehouse contact persons", description = "Update one or more existing warehouse contact person records")
    public ResponseEntity<ResponseData<String>> updateContactPersonWarehouse(
            @RequestBody @Valid List<ContactPersonWarehouseDto> body) {
        contactPersonWarehouseCoreService.update(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @DeleteMapping
    @Operation(summary = "Delete warehouse contact persons", description = "Delete one or more warehouse contact person records by IDs")
    public ResponseEntity<ResponseData<String>> deleteContactPersonWarehouse(
            @Parameter(description = "List of contact person IDs to delete") @RequestParam List<String> id) {
        contactPersonWarehouseCoreService
                .delete(id.stream().map(i -> ContactPersonWarehouseDto.builder().id(i).build()).toList());
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @GetMapping
    @Operation(summary = "Get warehouse contact persons", description = "Retrieve a paginated list of warehouse contact persons with search functionality")
    public ResponseEntity<ResponseMeta<ContactPersonWarehouseDto>> getWarehouses(
            @Parameter(description = "Search term for contact person name") @RequestParam String search,
            @ParameterObject @PageableDefault Pageable pageable) {
        var dto = ContactPersonWarehouseDto.builder().name(search).build();
        Page<ContactPersonWarehouseDto> cp = contactPersonWarehouseCoreService.getContacts(dto, pageable);
        return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, cp);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get contact person details", description = "Retrieve detailed information about a specific warehouse contact person")
    public ResponseEntity<ResponseData<ContactPersonWarehouseDto>> getDetailWarehouse(
            @Parameter(description = "Contact person ID") @PathVariable Optional<String> id) {
        var dto = ContactPersonWarehouseDto.builder()
                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                .build();
        ContactPersonWarehouseDto cp = contactPersonWarehouseCoreService.getContact(dto);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, cp);
    }
}
