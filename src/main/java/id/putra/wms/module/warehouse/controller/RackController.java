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

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.service.core.RackCoreService;
import id.putra.wms.shared.base.dto.response.ResponseData;
import id.putra.wms.shared.base.dto.response.ResponseMeta;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.exceptions.ModuleException;
import id.putra.wms.shared.helpers.ResponseHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/master-data/rack")
public class RackController {
    private final ResponseHelper responseHelper;
    private final RackCoreService rackCoreService;

    @PostMapping
    public ResponseEntity<ResponseData<String>> addRack(@RequestBody @Valid List<RackDto> body) {
        rackCoreService.add(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @PatchMapping
    public ResponseEntity<ResponseData<String>> updateRack(@RequestBody @Valid List<RackDto> body) {
        rackCoreService.update(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @DeleteMapping
    public ResponseEntity<ResponseData<String>> deleteRack(@RequestParam List<String> id) {
        rackCoreService.delete(id.stream().map(i -> RackDto.builder().id(i).build()).toList());
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @GetMapping
    public ResponseEntity<ResponseMeta<RackDto>> getRacks(
            @RequestParam Optional<String> search,
            @PageableDefault Pageable pageable) {

        var dto = RackDto.builder()
                .name(search.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                .build();
        Page<RackDto> rk = rackCoreService.getRacks(dto, pageable);
        return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, rk);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<RackDto>> getDetailRack(
            @PathVariable Optional<String> id) {
        var dto = RackDto.builder().id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM))).build();
        RackDto rk = rackCoreService.getRack(dto);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, rk);
    }
}
