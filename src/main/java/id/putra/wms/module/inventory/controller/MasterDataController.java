package id.putra.wms.module.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import id.putra.wms.shared.base.dto.param.SearchParam;
import id.putra.wms.shared.base.dto.response.thymeleaf.PagingResponse;
import jakarta.validation.Valid;

public interface MasterDataController<D> {

    // public ResponseEntity addOrUpdateProdut(
    // @RequestParam String action,
    // @Valid @RequestBody D dto,
    // BindingResult result,
    // RedirectAttributesModelMap redirect);

    // public ResponseEntity<PagingResponse<D>> getMasterData(@RequestBody
    // SearchParam body);
}
