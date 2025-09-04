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

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;
import id.putra.wms.module.warehouse.service.core.ContactPersonWarehouseCoreService;
import id.putra.wms.shared.base.dto.response.ResponseData;
import id.putra.wms.shared.base.dto.response.ResponseMeta;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.exceptions.ModuleException;
import id.putra.wms.shared.helpers.ResponseHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/master-data/contact-person-warehouse")
public class ContactPersonWarehouseController {
    private final ResponseHelper responseHelper;
    private final ContactPersonWarehouseCoreService contactPersonWarehouseCoreService;

    @PostMapping("/")
    public ResponseEntity<ResponseData<String>> addContactPersonWarehouse(
            @RequestBody @Valid List<ContactPersonWarehouseDto> body) {
        contactPersonWarehouseCoreService.add(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @PatchMapping("/")
    public ResponseEntity<ResponseData<String>> updateContactPersonWarehouse(
            @RequestBody @Valid List<ContactPersonWarehouseDto> body) {
        contactPersonWarehouseCoreService.update(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseData<String>> deleteContactPersonWarehouse(
            @RequestParam List<String> id) {
        contactPersonWarehouseCoreService
                .delete(id.stream().map(i -> ContactPersonWarehouseDto.builder().id(i).build()).toList());
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMeta<ContactPersonWarehouseDto>> getWarehouses(
            @RequestParam String search,
            @PageableDefault Pageable pageable) {
        var dto = ContactPersonWarehouseDto.builder().name(search).build();
        Page<ContactPersonWarehouseDto> cp = contactPersonWarehouseCoreService.getContacts(dto, pageable);
        return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, cp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<ContactPersonWarehouseDto>> getDetailWarehouse(
            @PathVariable Optional<String> id) {
        var dto = ContactPersonWarehouseDto.builder()
                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                .build();
        ContactPersonWarehouseDto cp = contactPersonWarehouseCoreService.getContact(dto);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, cp);
    }
}
