package id.putra.wms.module.inbound.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.putra.wms.shared.helpers.ResponseHelper;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/inbound")
@RequiredArgsConstructor
public class InboundController {
    private final ResponseHelper responseHelper;

}
