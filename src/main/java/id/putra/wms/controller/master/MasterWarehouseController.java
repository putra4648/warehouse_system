package id.putra.wms.controller.master;

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
import id.putra.wms.dto.LocationDto;
import id.putra.wms.dto.RackDto;
import id.putra.wms.dto.WarehouseDto;
import id.putra.wms.dto.ZoneDto;
import id.putra.wms.dto.param.SearchParam;
import id.putra.wms.dto.response.PagingResponse;
import id.putra.wms.service.impl.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class MasterWarehouseController implements MasterDataController<WarehouseDto> {

    private final WarehouseService service;
    Random random = new Random();
    private final List<WarehouseDto> fake = IntStream.range(0, 10).mapToObj(i -> {
        var warehouse = new WarehouseDto();
        warehouse.setName("Warehouse " + i);
        warehouse.setId("wh-" + i);

        // Fake Zones
        var zones = IntStream.range(0, random.nextInt(100)).mapToObj(a -> {
            var zone = new ZoneDto();
            zone.setId("zone-" + a);
            zone.setName("Zone " + a);

            var racks = IntStream.range(0, random.nextInt(30)).mapToObj(r -> {
                var rack = new RackDto();

                rack.setId("rack-" + r);
                rack.setName("Rack " + r);

                var locations = IntStream.range(0, random.nextInt(12)).mapToObj(l -> {
                    var location = new LocationDto();

                    location.setId("location-" + l);
                    location.setName("Location " + l);

                    return location;
                }).toList();

                rack.setLocations(locations);

                return rack;
            }).toList();

            zone.setRacks(racks);

            return zone;
        }).toList();

        warehouse.setZones(zones);

        return warehouse;

    }).toList();

    @GetMapping("master/warehouses")
    @Override
    public String page(Model model) {
        model.addAttribute("warehouses", fake);
        return "pages/master/warehouse/index";
    }

    @GetMapping("/master/warehouses/{id}")
    public String warehouse(Model model, @PathVariable Optional<String> id) {
        fake.stream().filter(wh -> wh.getId().equals(id.get())).findFirst().ifPresent(wh -> {
            model.addAttribute("warehouse", wh);
            model.addAttribute("zones", wh.getZones());
        });
        return "pages/master/warehouse/zone";
    }

    @GetMapping("/master/warehouses/{id}/zones/{zoneId}")
    public String warehouseZones(Model model, @PathVariable Optional<String> id,
            @PathVariable Optional<String> zoneId) {
        fake.stream().filter(wh -> wh.getId().equals(id.get())).findFirst().ifPresent(wh -> {
            model.addAttribute("warehouse", wh);

            wh.getZones().stream().filter(z -> z.getId().equals(zoneId.get())).findFirst().ifPresent(z -> {
                model.addAttribute("zone", z);

                var racks = z.getRacks().stream().toList();

                model.addAttribute("racks", racks);
            });

        });
        return "pages/master/warehouse/rack";
    }

    @GetMapping("/master/warehouses/{id}/zones/{zoneId}/racks/{rackId}")
    public String warehouseZoneLocations(Model model, @PathVariable Optional<String> id,
            @PathVariable Optional<String> zoneId, @PathVariable Optional<String> rackId) {
        fake.stream().filter(wh -> wh.getId().equals(id.get())).findFirst().ifPresent(wh -> {
            model.addAttribute("warehouse", wh);

            wh.getZones().stream().filter(z -> z.getId().equals(zoneId.get())).findFirst().ifPresent(z -> {
                model.addAttribute("zone", z);

                z.getRacks().stream().filter(r -> r.getId().equals(rackId.get())).findFirst().ifPresent(rack -> {
                    model.addAttribute("rack", rack);

                    var locations = rack.getLocations();

                    model.addAttribute("locations", locations);
                });

            });

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
        return ResponseEntity.ok().body(service.getAll(body));
    }

}
