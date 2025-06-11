package id.putra.wms.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import id.putra.wms.dto.ProductDto;
import id.putra.wms.dto.param.SearchParam;
import id.putra.wms.dto.response.PagingResponse;
import id.putra.wms.exceptions.ProductException;
import id.putra.wms.service.impl.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MasterDataController {

    private final ProductService productService;

    @GetMapping("master/product/{id}")
    public String getProductDetail(@PathVariable Optional<String> id, Model model) {
        if (id.isPresent()) {
            model.addAttribute("productForm", productService.getProductById(id.get()));
            model.addAttribute("showModal", true);
        }
        return "pages/master/product";
    }

    @GetMapping("master/product")
    public String getProduct(Model model) {
        model.addAttribute("productForm", new ProductDto());

        return "pages/master/product";
    }

    @PostMapping("master/product")
    public String postMasterProduct(@RequestParam String action, @Valid @ModelAttribute("productForm") ProductDto form,
            BindingResult result,
            RedirectAttributesModelMap redirect) {
        if (result.hasErrors()) {
            return "pages/master/product";
        }

        if (action.equals("edit")) {
            productService.update(form);
            String formattedMessage = """
                    <div
                      class="alert alert-success alert-dismissible"
                      role="alert"
                    >
                      <strong>Success </strong> %s
                      <button
                        type="button"
                        class="close"
                        data-dismiss="alert"
                        aria-label="Close"
                      >
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                              """.formatted("Product " + form.getSku() + " successfully updated");
            redirect.addFlashAttribute("messageHTML", formattedMessage);
        } else if (action.equals("add")) {
            productService.add(form);
            String formattedMessage = """
                    <div
                      class="alert alert-success alert-dismissible"
                      role="alert"
                    >
                      <strong>Success </strong> %s
                      <button
                        type="button"
                        class="close"
                        data-dismiss="alert"
                        aria-label="Close"
                      >
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                              """.formatted("Product " + form.getSku() + " successfully added");
            redirect.addFlashAttribute("messageHTML", formattedMessage);
        }
        return "redirect:/master/product";
    }

    @PostMapping("api/master/product")
    public ResponseEntity<PagingResponse<ProductDto>> getMasterProductList(@RequestBody SearchParam body) {
        return ResponseEntity.ok().body(productService.getAll(body));
    }

    @ExceptionHandler({ ProductException.class })
    public String exceptionHandler(ProductException exception, HttpServletRequest request,
            RedirectAttributesModelMap redirect) {
        String formattedMessage = """
                <div
                  class="alert alert-danger alert-dismissible"
                  role="alert"
                >
                  <strong>Error </strong> %s
                  <button
                    type="button"
                    class="close"
                    data-dismiss="alert"
                    aria-label="Close"
                  >
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                          """.formatted(exception.getMessage());
        redirect.addFlashAttribute("messageHTML", formattedMessage);
        return "redirect:" + request.getServletPath();
    }

}
