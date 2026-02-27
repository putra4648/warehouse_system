package id.putra.wms.module.inventory.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.putra.wms.module.inventory.dto.InventoryItemDto;
import id.putra.wms.module.inventory.service.core.InventoryItemCoreService;
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
@RequestMapping("/api/v1/inventory/items")
@Tag(name = "Inventory Items", description = "Inventory item management APIs")
@SecurityRequirement(name = "oauth2")
public class InventoryItemController {
    private final ResponseHelper responseHelper;
    private final InventoryItemCoreService inventoryItemCoreService;

    @PostMapping
    @Operation(summary = "Add new inventory items", description = "Create one or more new inventory item records")
    public ResponseEntity<ResponseData<String>> addInventoryItem(@RequestBody @Valid List<InventoryItemDto> body) {
        inventoryItemCoreService.save(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @PutMapping
    @Operation(summary = "Update inventory items", description = "Update one or more existing inventory item records")
    public ResponseEntity<ResponseData<String>> updateInventoryItem(@RequestBody @Valid List<InventoryItemDto> body) {
        inventoryItemCoreService.update(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @DeleteMapping
    @Operation(summary = "Delete inventory items", description = "Delete one or more inventory item records by IDs")
    public ResponseEntity<ResponseData<String>> deleteInventoryItem(
            @Parameter(description = "List of inventory item IDs to delete") @RequestParam @Valid List<Long> id) {
        inventoryItemCoreService.delete(id.stream().map(i -> InventoryItemDto.builder().id(i).build()).toList());
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @GetMapping
    @Operation(summary = "Get inventory items", description = "Retrieve a paginated list of inventory items with search functionality")
    public ResponseEntity<ResponseMeta<InventoryItemDto>> getInventoryItems(
            @Parameter(description = "Search term for item name") @RequestParam("search") String search,
            @ParameterObject @PageableDefault Pageable pageable) {
        var dto = InventoryItemDto.builder().name(search).build();
        Page<InventoryItemDto> items = inventoryItemCoreService.getInventoryItems(dto, pageable);
        return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, items);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get inventory item details", description = "Retrieve detailed information about a specific inventory item")
    public ResponseEntity<ResponseData<InventoryItemDto>> getDetailInventoryItem(
            @Parameter(description = "Inventory Item ID") @PathVariable Optional<Long> id) {
        var dto = InventoryItemDto.builder()
                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                .build();
        InventoryItemDto item = inventoryItemCoreService.getInventoryItem(dto);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, item);
    }

}
