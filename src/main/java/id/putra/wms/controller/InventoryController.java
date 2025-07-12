package id.putra.wms.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import id.putra.wms.component.MessageBox;
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
        var msgbox = new ArrayList<MessageBox>();
        msgbox.add(new MessageBox("fas fa-cubes", "Total Items", 50_000L, "text-bg-primary"));
        msgbox.add(new MessageBox("fas fa-exclamation-triangle", "Low Stocks", 10_000L, "text-bg-warning"));
        msgbox.add(new MessageBox("fas fa-cubes", "Out of Stocks", 20_000L, "text-bg-danger"));

        model.addAttribute("form", new InventoryDto());
        model.addAttribute("msgbox", msgbox);

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
