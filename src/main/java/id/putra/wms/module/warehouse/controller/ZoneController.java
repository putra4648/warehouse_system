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

import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.service.core.ZoneCoreService;
import id.putra.wms.shared.base.dto.response.ResponseData;
import id.putra.wms.shared.base.dto.response.ResponseMeta;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.exceptions.ModuleException;
import id.putra.wms.shared.helpers.ResponseHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/master-data/zone")
public class ZoneController {
    private final ResponseHelper responseHelper;
    private final ZoneCoreService zoneCoreService;

    @PostMapping
    public ResponseEntity<ResponseData<String>> addZone(@RequestBody @Valid List<ZoneDto> body) {
        zoneCoreService.add(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @PatchMapping
    public ResponseEntity<ResponseData<String>> updateZone(@RequestBody @Valid List<ZoneDto> body) {
        zoneCoreService.update(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @DeleteMapping
    public ResponseEntity<ResponseData<String>> deleteZone(@RequestParam List<String> id) {
        zoneCoreService.delete(id.stream().map(i -> ZoneDto.builder().id(i).build()).toList());
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @GetMapping
    public ResponseEntity<ResponseMeta<ZoneDto>> getZones(@PathVariable Optional<String> id,
            @PageableDefault Pageable pageable) {
        var dto = ZoneDto.builder()
                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                .build();
        Page<ZoneDto> zn = zoneCoreService.getZones(dto, pageable);
        return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, zn);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<ZoneDto>> getDetailZone(
            @PathVariable Optional<String> id) {
        var zoneDto = ZoneDto.builder()
                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                .build();
        ZoneDto zn = zoneCoreService.getZone(zoneDto);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, zn);
    }
}
