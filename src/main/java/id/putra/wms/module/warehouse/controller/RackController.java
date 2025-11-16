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

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.service.core.RackCoreService;
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
@RequestMapping("/api/v1/master/rack")
@Tag(name = "Rack", description = "Rack management APIs")
@SecurityRequirement(name = "oauth2")
public class RackController {
    private final ResponseHelper responseHelper;
    private final RackCoreService rackCoreService;

    @PostMapping
    @Operation(summary = "Add new racks", description = "Create one or more new rack records")
    public ResponseEntity<ResponseData<String>> addRack(@RequestBody @Valid List<RackDto> body) {
        rackCoreService.add(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @PatchMapping
    @Operation(summary = "Update racks", description = "Update one or more existing rack records")
    public ResponseEntity<ResponseData<String>> updateRack(@RequestBody @Valid List<RackDto> body) {
        rackCoreService.update(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @DeleteMapping
    @Operation(summary = "Delete racks", description = "Delete one or more rack records by IDs")
    public ResponseEntity<ResponseData<String>> deleteRack(
            @Parameter(description = "List of rack IDs to delete") @RequestParam List<String> id) {
        rackCoreService.delete(id.stream().map(i -> RackDto.builder().id(i).build()).toList());
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @GetMapping
    @Operation(summary = "Get racks", description = "Retrieve a paginated list of racks with search functionality")
    public ResponseEntity<ResponseMeta<RackDto>> getRacks(
            @Parameter(description = "Search term for rack name") @RequestParam Optional<String> search,
            @ParameterObject @PageableDefault Pageable pageable) {

        var dto = RackDto.builder()
                .name(search.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                .build();
        Page<RackDto> rk = rackCoreService.getRacks(dto, pageable);
        return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, rk);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get rack details", description = "Retrieve detailed information about a specific rack")
    public ResponseEntity<ResponseData<RackDto>> getDetailRack(
            @Parameter(description = "Rack ID") @PathVariable Optional<String> id) {
        var dto = RackDto.builder().id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM))).build();
        RackDto rk = rackCoreService.getRack(dto);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, rk);
    }
}
