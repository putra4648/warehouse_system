package id.putra.wms.module.inbound.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.putra.wms.module.inbound.dto.LabellingDto;
import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.dto.PutawayDto;
import id.putra.wms.module.inbound.dto.QualityInspectionDto;
import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.module.inbound.dto.ReturnDto;
import id.putra.wms.module.inbound.service.core.InspectionService;
import id.putra.wms.module.inbound.service.core.LabellingService;
import id.putra.wms.module.inbound.service.core.PurchaseOrderService;
import id.putra.wms.module.inbound.service.core.PutawayService;
import id.putra.wms.module.inbound.service.core.ReceivingOrderService;
import id.putra.wms.module.inbound.service.core.ReturnInboundService;
import id.putra.wms.shared.base.dto.response.ResponseData;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.helpers.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/inbound")
@RequiredArgsConstructor
@Tag(name = "Inbound", description = "Inbound process management APIs")
@SecurityRequirement(name = "oauth2")
public class InboundController {

    private final ResponseHelper responseHelper;
    private final PurchaseOrderService purchaseOrderService;
    private final ReceivingOrderService receivingOrderService;
    private final InspectionService inspectionService;
    private final PutawayService putawayService;
    private final ReturnInboundService returnInboundService;
    private final LabellingService labellingService;

    // --- Purchase Order ---

    @PostMapping("/po")
    @Operation(summary = "Create Purchase Order", description = "Create a new purchase order")
    public ResponseEntity<ResponseData<PurchaseOrderDto>> createPO(@RequestBody @Valid PurchaseOrderDto body) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, purchaseOrderService.create(body));
    }

    @GetMapping("/po/{id}")
    @Operation(summary = "Get PO by ID", description = "Retrieve a purchase order by its ID")
    public ResponseEntity<ResponseData<PurchaseOrderDto>> getPOById(@PathVariable Long id) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, purchaseOrderService.getById(id));
    }

    // --- Receiving ---

    @PostMapping("/receiving")
    @Operation(summary = "Create Receiving Record", description = "Start the receiving process for a PO")
    public ResponseEntity<ResponseData<ReceivingDto>> createReceiving(@RequestBody @Valid ReceivingDto body) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, receivingOrderService.create(body));
    }

    @GetMapping("/receiving/{id}")
    @Operation(summary = "Get Receiving by ID")
    public ResponseEntity<ResponseData<ReceivingDto>> getReceivingById(@PathVariable Long id) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, receivingOrderService.getById(id));
    }

    // --- Inspection ---

    @PostMapping("/inspection")
    @Operation(summary = "Create Inspection Record", description = "Record results of a quality inspection")
    public ResponseEntity<ResponseData<QualityInspectionDto>> createInspection(
            @RequestBody @Valid QualityInspectionDto body) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, inspectionService.create(body));
    }

    // --- Putaway ---

    @PostMapping("/putaway")
    @Operation(summary = "Create Putaway Record", description = "Record the movement of items to their storage locations")
    public ResponseEntity<ResponseData<PutawayDto>> createPutaway(@RequestBody @Valid PutawayDto body) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, putawayService.create(body));
    }

    // --- Others (Labelling, Return) ---

    @PostMapping("/labelling")
    @Operation(summary = "Create Labelling Record")
    public ResponseEntity<ResponseData<LabellingDto>> createLabelling(@RequestBody @Valid LabellingDto body) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, labellingService.create(body));
    }

    @PostMapping("/return")
    @Operation(summary = "Create Return Record", description = "Record returns to the vendor")
    public ResponseEntity<ResponseData<ReturnDto>> createReturn(@RequestBody @Valid ReturnDto body) {
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, returnInboundService.create(body));
    }
}
