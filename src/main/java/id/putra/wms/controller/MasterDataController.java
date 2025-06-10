package id.putra.wms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import id.putra.wms.dto.ProductDto;
import jakarta.validation.Valid;

@Controller
public class MasterDataController {

    // @GetMapping("master/{path}")
    // public String masterPage(Model model, @PathVariable String path) {
    // switch (path) {
    // case "product":
    // model.addAttribute("form", new ProductDto());
    // return "pages/master/product";
    // case "location":
    // return "pages/master/location";
    // case "supplier":
    // return "pages/master/supplier";
    // case "configuration":
    // return "pages/master/configuration";
    // default:
    // return "pages/not_found";
    // }
    // }

    @GetMapping("master/product")
    public String getMasterProduct(Model model) {
        model.addAttribute("productForm", new ProductDto());
        return "pages/master/product";
    }

    @PostMapping("master/product")
    public String postMasterProduct(@Valid @ModelAttribute("productForm") ProductDto form, BindingResult result,
            Model model) {
        System.out.println("FORM " + form);
        if (result.hasErrors()) {
            return "pages/master/product";
        }
        return "redirect:/master/product";
    }

}
