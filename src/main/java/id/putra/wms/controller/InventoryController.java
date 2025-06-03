package id.putra.wms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import id.putra.wms.dto.InventoryDto;
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

    @GetMapping("show-inventory")
    @ResponseBody
    public ResponseEntity<List<?>> getMethodName() {
        return ResponseEntity.ok().body(inventoryService.getAll());
    }

    @PostMapping("inventory")
    public String addInventory(@ModelAttribute InventoryDto form) {
        inventoryService.addInventory(form);
        return "redirect:/inventory";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
