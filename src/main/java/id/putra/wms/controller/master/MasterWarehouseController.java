package id.putra.wms.controller.master;

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

import id.putra.wms.constant.MessageConstant;
import id.putra.wms.dto.RackDto;
import id.putra.wms.dto.WarehouseDto;
import id.putra.wms.dto.ZoneDto;
import id.putra.wms.dto.param.SearchParam;
import id.putra.wms.dto.response.PagingResponse;
import id.putra.wms.service.impl.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MasterWarehouseController implements MasterDataController<WarehouseDto> {

    private final WarehouseService service;

    @GetMapping("master/warehouses")
    // @Override
    public String page(Model model, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        var s = new SearchParam();
        s.setPage(page.get());
        s.setSize(size.get());

        Page<WarehouseDto> warehouse = service.getAll(s);

        model.addAttribute("page", warehouse);
        model.addAttribute("size", size.get());
        model.addAttribute("warehouses", warehouse.getContent());

        return "pages/master/warehouse/index";
    }

    @GetMapping("/master/warehouses/{id}")
    public String warehouse(Model model, @PathVariable Optional<String> id) {
        id.ifPresent(i -> {
            WarehouseDto wh = service.getDataById(i);
            model.addAttribute("warehouse", wh);
            model.addAttribute("zones", wh.getZones());
        });
        return "pages/master/warehouse/zone";
    }

    @GetMapping("/master/warehouses/{id}/zones/{zoneId}")
    public String warehouseZones(Model model, @PathVariable Optional<String> id,
            @PathVariable Optional<String> zoneId) {
        id.ifPresent(i -> {
            WarehouseDto wh = service.getDataById(i);
            model.addAttribute("warehouse", wh);

            ZoneDto zn = service.getRackByZoneID(zoneId.get());
            model.addAttribute("zone", zn);

            model.addAttribute("racks", zn.getRacks());
        });
        return "pages/master/warehouse/rack";
    }

    @GetMapping("/master/warehouses/{id}/zones/{zoneId}/racks/{rackId}")
    public String warehouseZoneLocations(Model model, @PathVariable Optional<String> id,
            @PathVariable Optional<String> zoneId, @PathVariable Optional<String> rackId) {

        id.ifPresent(i -> {
            WarehouseDto wh = service.getDataById(i);
            model.addAttribute("warehouse", wh);

            ZoneDto zn = service.getRackByZoneID(zoneId.get());
            model.addAttribute("zone", zn);

            model.addAttribute("warehouse", wh);

            RackDto rk = service.getLocationByRack(rackId.get());
            model.addAttribute("rack", rk);

            var locations = rk.getLocations();

            model.addAttribute("locations", locations);
        });

        return "pages/master/warehouse/location";
    }

    @GetMapping("master/warehouses/add")
    public String addPage(Model model) {
        model.addAttribute("warehouseForm", new WarehouseDto());
        return "pages/master/warehouse/add";
    }

    @PostMapping("master/warehouses")
    @Override
    public String addOrUpdateProdut(String action, @Valid WarehouseDto form, BindingResult result,
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
    public ResponseEntity<PagingResponse<WarehouseDto>> getMasterData(SearchParam body) {
        // return ResponseEntity.ok().body(service.getAll(body));
        return null;
    }

}
