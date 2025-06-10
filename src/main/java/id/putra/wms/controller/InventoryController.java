package id.putra.wms.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import id.putra.wms.dto.InventoryDto;
import id.putra.wms.dto.param.SearchParam;
import id.putra.wms.service.InventoryService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("inventory")
    public String inventory(Model model) {
        model.addAttribute("form", new InventoryDto());
        return "pages/inventory";
    }

    @PostMapping("api/inventory")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> showInventory(@RequestBody SearchParam param) {
        return ResponseEntity.ok().body(inventoryService.getAll(param));
    }

    @PostMapping("inventory")
    public String addInventory(@ModelAttribute InventoryDto form) {
        inventoryService.addInventory(form);
        return "redirect:/inventory";
    }

}
