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

import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.service.core.LocationCoreService;
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
@RequestMapping("/api/v1/master/location")
@Tag(name = "Location", description = "Location management APIs")
@SecurityRequirement(name = "oauth2")
public class LocationController {
    private final ResponseHelper responseHelper;
    private final LocationCoreService locationCoreService;

    @PostMapping
    @Operation(summary = "Add new locations", description = "Create one or more new location records")
    public ResponseEntity<ResponseData<LocationDto>> addLocation(@RequestBody @Valid List<LocationDto> body) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, null);
    }

    @PatchMapping
    @Operation(summary = "Update locations", description = "Update one or more existing location records")
    public ResponseEntity<ResponseData<String>> updateLocation(@RequestBody @Valid List<LocationDto> body) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @DeleteMapping
    @Operation(summary = "Delete locations", description = "Delete one or more location records by IDs")
    public ResponseEntity<ResponseData<String>> deleteLocation(
            @Parameter(description = "List of location IDs to delete") @RequestBody List<String> body) {
        locationCoreService.delete(body.stream().map(i -> LocationDto.builder().id(i).build()).toList());
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @GetMapping
    @Operation(summary = "Get locations", description = "Retrieve a paginated list of locations with search functionality")
    public ResponseEntity<ResponseMeta<LocationDto>> getLocations(
            @Parameter(description = "Search term for location") @RequestParam Optional<String> search,
            @ParameterObject @PageableDefault Pageable pageable) {
        var dto = LocationDto.builder()
                .id(search.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                .build();

        Page<LocationDto> lo = locationCoreService.getLocations(dto, pageable);
        return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, lo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get location details", description = "Retrieve detailed information about a specific location")
    public ResponseEntity<ResponseData<LocationDto>> getDetailLocation(
            @Parameter(description = "Location ID") @PathVariable Optional<String> id) {
        var dto = LocationDto.builder().id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                .build();
        var lo = locationCoreService.getLocation(dto);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, lo);
    }
}
