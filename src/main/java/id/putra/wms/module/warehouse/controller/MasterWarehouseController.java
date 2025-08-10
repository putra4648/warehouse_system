package id.putra.wms.module.warehouse.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import id.putra.wms.module.inventory.controller.MasterDataController;
import id.putra.wms.module.inventory.service.core.WarehouseService;
import id.putra.wms.module.warehouse.dto.form.RackForm;
import id.putra.wms.module.warehouse.dto.form.WarehouseForm;
import id.putra.wms.module.warehouse.dto.form.ZoneForm;
import id.putra.wms.shared.base.dto.param.SearchParam;
import id.putra.wms.shared.base.dto.response.thymeleaf.PagingResponse;
import id.putra.wms.shared.constants.MessageConstant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MasterWarehouseController implements MasterDataController<WarehouseForm> {

    private final WarehouseService service;

    @GetMapping("master/warehouses")
    // @Override
    public String page(Model model, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        var s = new SearchParam();
        page.ifPresent(pg -> s.setPage(pg));
        size.ifPresent(sz -> s.setSize(sz));

        Page<WarehouseForm> warehouse = service.getAll(s);

        model.addAttribute("page", warehouse);
        model.addAttribute("size", warehouse.getSize());
        model.addAttribute("warehouses", warehouse.getContent());

        return "pages/master/warehouse/index";
    }

    @GetMapping("/master/warehouses/{id}")
    public String warehouseZones(Model model, @PathVariable Optional<String> id) {
        id.ifPresent(i -> {
            WarehouseForm wh = service.getDataById(i);
            model.addAttribute("warehouse", wh);
            model.addAttribute("zones", wh.getZones());
        });
        return "pages/master/warehouse/zone";
    }

    @GetMapping("/master/warehouses/{id}/zones/{zoneId}")
    public String warehouseRacksByZone(Model model, @PathVariable Optional<String> id,
            @PathVariable Optional<String> zoneId) {
        id.ifPresent(i -> {
            WarehouseForm wh = service.getDataById(i);
            model.addAttribute("warehouse", wh);

            ZoneForm zn = service.getRackByZoneID(zoneId.get());
            model.addAttribute("zone", zn);

            model.addAttribute("racks", zn.getRacks());
        });
        return "pages/master/warehouse/rack";
    }

    @GetMapping("/master/warehouses/{id}/zones/{zoneId}/racks/{rackId}")
    public String warehouseLocationsByRack(Model model, @PathVariable Optional<String> id,
            @PathVariable Optional<String> zoneId, @PathVariable Optional<String> rackId) {

        id.ifPresent(i -> {
            WarehouseForm wh = service.getDataById(i);
            model.addAttribute("warehouse", wh);

            ZoneForm zn = service.getRackByZoneID(zoneId.get());
            model.addAttribute("zone", zn);

            model.addAttribute("warehouse", wh);

            RackForm rk = service.getLocationByRack(rackId.get());
            model.addAttribute("rack", rk);

            var locations = rk.getLocations();

            model.addAttribute("locations", locations);
        });

        return "pages/master/warehouse/location";
    }

    @GetMapping("master/warehouses/add")
    public String addPage(Model model) {
        model.addAttribute("warehouseForm", new WarehouseForm());
        return "pages/master/warehouse/add";
    }

    @PostMapping("master/warehouses")
    @Override
    public String addOrUpdateProdut(String action, @Valid WarehouseForm form, BindingResult result,
            RedirectAttributesModelMap redirect) {
        if (result.hasErrors()) {
            return "pages/master/warehouse/index";
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

        return "redirect:/master/warehouses";
    }

    @PostMapping("api/master/warehouses")
    @Override
    public ResponseEntity<PagingResponse<WarehouseForm>> getMasterData(SearchParam body) {
        // return ResponseEntity.ok().body(service.getAll(body));
        return null;
    }

}
