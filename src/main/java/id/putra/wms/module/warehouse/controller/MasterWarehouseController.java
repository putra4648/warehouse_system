package id.putra.wms.module.warehouse.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.putra.wms.config.exception.ModuleException;
import id.putra.wms.module.inventory.service.core.WarehouseService;
import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.shared.base.dto.response.ResponseData;
import id.putra.wms.shared.base.dto.response.ResponseMeta;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.helpers.ResponseHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/master-data/warehouse")
public class MasterWarehouseController {

        private final ResponseHelper responseHelper;
        private final WarehouseService service;

        @PostMapping("/")
        public ResponseEntity<ResponseData<WarehouseDto>> addWarehouse(@RequestBody @Valid WarehouseDto body) {
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, null);
        }

        @GetMapping("/")
        public ResponseEntity<ResponseMeta<WarehouseDto>> getWarehouses(@RequestParam("search") String search,
                        @PageableDefault Pageable pageable) {
                var dto = WarehouseDto.builder().name(search).build();
                Page<WarehouseDto> warehouse = service.getWarehouses(dto, pageable);
                return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, warehouse);
        }

        @GetMapping("/{id}")
        public ResponseEntity<ResponseData<WarehouseDto>> getDetailWarehouse(@PathVariable Optional<String> id) {
                var dto = WarehouseDto.builder()
                                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                                .build();
                WarehouseDto wh = service.getWarehouseById(dto);
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, wh);
        }

        @PostMapping("/{id}/zone")
        public ResponseEntity<ResponseData<ZoneDto>> addZone(@RequestBody @Valid ZoneDto body) {
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, null);
        }

        @GetMapping("/{id}/zone")
        public ResponseEntity<ResponseMeta<ZoneDto>> getZones(@PathVariable Optional<String> id,
                        @PageableDefault Pageable pageable) {
                var dto = WarehouseDto.builder()
                                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                                .build();
                Page<ZoneDto> zn = service.getZonesByWarehouseId(dto, pageable);
                return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, zn);
        }

        @GetMapping("/{id}/zones/{zoneId}")
        public ResponseEntity<ResponseData<ZoneDto>> getDetailZone(
                        @PathVariable Optional<String> id,
                        @PathVariable Optional<String> zoneId) {
                var dto = WarehouseDto.builder()
                                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                                .build();
                var zoneDto = ZoneDto.builder()
                                .id(zoneId.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                                .build();
                ZoneDto zn = service.getZoneByIdByWarehouseId(dto, zoneDto);
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, zn);
        }

        @PostMapping("/{id}/zone/{zoneId}/rack")
        public ResponseEntity<ResponseData<RackDto>> addRack(@RequestBody @Valid RackDto body) {
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, null);
        }

        @GetMapping("/{id}/zone/{zoneId}/rack")
        public ResponseEntity<ResponseMeta<RackDto>> getRacks(
                        @PathVariable Optional<String> id,
                        @PathVariable Optional<String> zoneId,
                        @PageableDefault Pageable pageable) {
                var dto = WarehouseDto.builder()
                                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                                .build();
                var zoneDto = ZoneDto.builder()
                                .id(zoneId.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                                .build();
                Page<RackDto> rk = service.getRacks(dto, zoneDto, pageable);
                return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, rk);
        }

        @GetMapping("/{id}/zone/{zoneId}/rack/{rackId}")
        public ResponseEntity<ResponseData<RackDto>> getDetailRack(
                        @PathVariable Optional<String> id,
                        @PathVariable Optional<String> zoneId,
                        @PathVariable Optional<String> rackId) {

                return responseHelper.createResponseData(ResponseEnum.SUCCESS, null);
        }

        @PostMapping("/{id}/zone/{zoneId}/rack/location")
        public ResponseEntity<ResponseData<LocationDto>> addLocation(@RequestBody @Valid LocationDto body) {
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, null);
        }

        @GetMapping("/{id}/zone/{zoneId}/rack/{rackId}/location")
        public ResponseEntity<ResponseMeta<RackDto>> getLocations(
                        @PathVariable Optional<String> id,
                        @PathVariable Optional<String> zoneId,
                        @PathVariable Optional<String> rackId,
                        @PageableDefault Pageable pageable) {
                var dto = WarehouseDto.builder()
                                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                                .build();
                var zoneDto = ZoneDto.builder()
                                .id(zoneId.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                                .build();
                Page<RackDto> rk = service.getRacks(dto, zoneDto, pageable);
                return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, rk);
        }

        @GetMapping("/{id}/zone/{zoneId}/rack/{rackId}/location/{locationId}")
        public ResponseEntity<ResponseData<RackDto>> getDetailLocation(
                        @PathVariable Optional<String> id,
                        @PathVariable Optional<String> zoneId,
                        @PathVariable Optional<String> rackId,
                        @PathVariable Optional<String> locationId) {
                return responseHelper.createResponseData(ResponseEnum.SUCCESS, null);
        }

}
