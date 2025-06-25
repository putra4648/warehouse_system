package id.putra.wms.controller.master;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import id.putra.wms.constant.MessageConstant;
import id.putra.wms.dto.WarehouseDto;
import id.putra.wms.dto.param.SearchParam;
import id.putra.wms.dto.response.PagingResponse;
import id.putra.wms.service.impl.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MasterWarehouseController implements MasterDataController<WarehouseDto> {

    private final WarehouseService service;

    @GetMapping("master/warehouse")
    @Override
    public String page(Model model) {
        return "pages/master/warehouse";
    }

    @GetMapping("master/warehouse/add")

    public String addPage(Model model) {
        model.addAttribute("warehouseForm", new WarehouseDto());
        return "pages/master/warehouse-add";
    }

    @PostMapping("master/warehouse")
    @Override
    public String addOrUpdateProdut(String action, @Valid WarehouseDto form, BindingResult result,
            RedirectAttributesModelMap redirect) {
        if (result.hasErrors()) {
            return "pages/master/warehouse";
        }

        if (action.equals("edit")) {
            service.update(form);
            redirect.addFlashAttribute("messageHTML",
                    MessageConstant.MESSAGE.formatted("alert-success", "Success",
                            "Warehouse " + form.getId() + " successfully updated"));
        }
        if (action.equals("add")) {
            service.add(form);
            redirect.addFlashAttribute("messageHTML",
                    MessageConstant.MESSAGE.formatted("alert-success", "Success",
                            "Warehouse " + form.getId() + " successfully added"));
        }

        if (action.equals("delete")) {
            service.delete(form.getId());
            redirect.addFlashAttribute("messageHTML",
                    MessageConstant.MESSAGE.formatted("alert-success", "Success",
                            "Warehouse " + form.getId() + " successfully deleted"));
        }

        return "redirect:/master/warehouse";
    }

    @PostMapping("api/master/warehouse")
    @Override
    public ResponseEntity<PagingResponse<WarehouseDto>> getMasterData(SearchParam body) {
        return ResponseEntity.ok().body(service.getAll(body));
    }

}
