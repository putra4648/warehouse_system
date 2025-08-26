package id.putra.wms.module.inventory.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import id.putra.wms.module.inventory.dto.form.InventoryForm;
import id.putra.wms.module.inventory.service.core.InventoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // @PostMapping("api/inventory")
    // @ResponseBody
    // public ResponseEntity<Map<String, Object>> showInventory(@RequestBody
    // SearchParam param) {
    // return ResponseEntity.ok().body(inventoryService.getAll(param));
    // }

    @PostMapping("inventory")
    public String addInventory(@ModelAttribute InventoryForm form) {
        inventoryService.addInventory(form);
        return "redirect:/inventory";
    }

}
