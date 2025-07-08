package id.putra.wms.controller.master;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import id.putra.wms.dto.param.SearchParam;
import id.putra.wms.dto.response.PagingResponse;
import jakarta.validation.Valid;

public interface MasterDataController<Form> {

    public String page(Model model);

    public String addOrUpdateProdut(
            @RequestParam String action,
            @Valid @ModelAttribute("form") Form form,
            BindingResult result,
            RedirectAttributesModelMap redirect);

    public ResponseEntity<PagingResponse<Form>> getMasterData(@RequestBody SearchParam body);
}
