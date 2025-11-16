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

import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.service.core.ZoneCoreService;
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
@RequestMapping("/api/v1/master/zone")
@Tag(name = "Zone", description = "Zone management APIs")
@SecurityRequirement(name = "oauth2")
public class ZoneController {
    private final ResponseHelper responseHelper;
    private final ZoneCoreService zoneCoreService;

    @PostMapping
    @Operation(summary = "Add new zones", description = "Create one or more new zone records")
    public ResponseEntity<ResponseData<String>> addZone(@RequestBody @Valid List<ZoneDto> body) {
        zoneCoreService.add(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @PatchMapping
    @Operation(summary = "Update zones", description = "Update one or more existing zone records")
    public ResponseEntity<ResponseData<String>> updateZone(@RequestBody @Valid List<ZoneDto> body) {
        zoneCoreService.update(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @DeleteMapping
    @Operation(summary = "Delete zones", description = "Delete one or more zone records by IDs")
    public ResponseEntity<ResponseData<String>> deleteZone(
            @Parameter(description = "List of zone IDs to delete") @RequestParam List<String> id) {
        zoneCoreService.delete(id.stream().map(i -> ZoneDto.builder().id(i).build()).toList());
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @GetMapping
    @Operation(summary = "Get zones", description = "Retrieve a paginated list of zones")
    public ResponseEntity<ResponseMeta<ZoneDto>> getZones(
            @Parameter(description = "Zone ID") @PathVariable Optional<String> id,
            @ParameterObject @PageableDefault Pageable pageable) {
        var dto = ZoneDto.builder()
                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                .build();
        Page<ZoneDto> zn = zoneCoreService.getZones(dto, pageable);
        return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, zn);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get zone details", description = "Retrieve detailed information about a specific zone")
    public ResponseEntity<ResponseData<ZoneDto>> getDetailZone(
            @Parameter(description = "Zone ID") @PathVariable Optional<String> id) {
        var zoneDto = ZoneDto.builder()
                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                .build();
        ZoneDto zn = zoneCoreService.getZone(zoneDto);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, zn);
    }
}
