package id.putra.wms.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import id.putra.wms.service.InventoryService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("inventory")
    public String inventory(Model model) {
        model.addAttribute("form", new InventoryForm());
        return "pages/inventory";
    }

    @PostMapping("inventory")
    public String addInventory(@ModelAttribute InventoryForm form) {
        var body = new HashMap<String, Object>();
        body.put("name", form.getName());
        inventoryService.addInventory(body);

        return "redirect:/inventory";
    }

    @Data
    class InventoryForm {
        private String name;
    }

}
