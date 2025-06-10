package id.putra.wms.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import id.putra.wms.dto.ProductDto;
import id.putra.wms.exceptions.ProductException;
import id.putra.wms.service.impl.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MasterDataController implements ErrorController {

    private final ProductService productService;

    @GetMapping("master/{path}")
    public String masterPage(Model model, @PathVariable String path) {
        switch (path) {
            case "product":
                model.addAttribute("productForm", new ProductDto());
                return "pages/master/product";
            case "location":
                return "pages/master/location";
            case "supplier":
                return "pages/master/supplier";
            case "configuration":
                return "pages/master/configuration";
            default:
                return "pages/not_found";
        }
    }

    @PostMapping("master/product")
    public String postMasterProduct(@Valid @ModelAttribute("productForm") ProductDto form, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "pages/master/product";
        }
        productService.add(form);
        return "redirect:/master/product";
    }

    @ExceptionHandler(exception = { ProductException.class })
    public String exceptionHandler(ProductException exception, HttpServletRequest request, Model model) {
        exception.printStackTrace();
        model.addAttribute("message", exception.getMessage());
        return "redirect:/master" + request.getServletPath();
    }

}
