package id.putra.wms.module.outbound.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.putra.wms.module.outbound.dto.CarrierDto;
import id.putra.wms.module.outbound.dto.PackingDto;
import id.putra.wms.module.outbound.dto.PickingTaskDto;
import id.putra.wms.module.outbound.dto.SalesOrderDto;
import id.putra.wms.module.outbound.dto.ShipmentDto;
import id.putra.wms.module.outbound.service.core.CarrierService;
import id.putra.wms.module.outbound.service.core.PackingService;
import id.putra.wms.module.outbound.service.core.PickingService;
import id.putra.wms.module.outbound.service.core.SalesOrderService;
import id.putra.wms.module.outbound.service.core.ShippingService;
import id.putra.wms.shared.base.dto.response.ResponseData;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.helpers.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/outbound")
@RequiredArgsConstructor
@Tag(name = "Outbound", description = "Outbound process management APIs")
@SecurityRequirement(name = "oauth2")
public class OutboundController {

    private final ResponseHelper responseHelper;
    private final SalesOrderService salesOrderService;
    private final PickingService pickingService;
    private final PackingService packingService;
    private final ShippingService shippingService;
    private final CarrierService carrierService;

    // --- Sales Order ---

    @PostMapping("/so")
    @Operation(summary = "Create Sales Order")
    public ResponseEntity<ResponseData<SalesOrderDto>> createSO(@RequestBody @Valid SalesOrderDto body) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, salesOrderService.create(body));
    }

    @GetMapping("/so/{id}")
    @Operation(summary = "Get SO by ID")
    public ResponseEntity<ResponseData<SalesOrderDto>> getSOById(@PathVariable Long id) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, salesOrderService.getById(id));
    }

    // --- Picking Task ---

    @PostMapping("/picking")
    @Operation(summary = "Create Picking Task")
    public ResponseEntity<ResponseData<PickingTaskDto>> createPicking(@RequestBody @Valid PickingTaskDto body) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, pickingService.create(body));
    }

    @GetMapping("/picking/{id}")
    @Operation(summary = "Get Picking Task by ID")
    public ResponseEntity<ResponseData<PickingTaskDto>> getPickingById(@PathVariable Long id) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, pickingService.getById(id));
    }

    // --- Packing ---

    @PostMapping("/packing")
    @Operation(summary = "Create Packing Record")
    public ResponseEntity<ResponseData<PackingDto>> createPacking(@RequestBody @Valid PackingDto body) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, packingService.create(body));
    }

    // --- Shipping ---

    @PostMapping("/shipping")
    @Operation(summary = "Create Shipment Record")
    public ResponseEntity<ResponseData<ShipmentDto>> createShipment(@RequestBody @Valid ShipmentDto body) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, shippingService.create(body));
    }

    // --- Carrier ---

    @PostMapping("/carrier")
    @Operation(summary = "Create Carrier")
    public ResponseEntity<ResponseData<CarrierDto>> createCarrier(@RequestBody @Valid CarrierDto body) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, carrierService.create(body));
    }
}
