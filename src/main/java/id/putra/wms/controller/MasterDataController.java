package id.putra.wms.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class MasterDataController implements ErrorController {

  private final ProductService productService;
  private final String MESSAGE = """
      <div
        class="alert %s alert-dismissible"
        role="alert"
      >
        <strong>%s </strong> %s
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="alert"
          aria-label="Close"
        >
        </button>
      </div>
            """;

  @GetMapping("master/product")
  public String getProduct(Model model) {
    model.addAttribute("productForm", new ProductDto());
    return "pages/master/product";
  }

  @PostMapping("master/product")
  public String addOrUpdateProdut(
      @RequestParam String action,
      @Valid @ModelAttribute("productForm") ProductDto form,
      BindingResult result,
      RedirectAttributesModelMap redirect) {
    if (result.hasErrors()) {
      return "pages/master/product";
    }

    if (action.equals("edit")) {
      productService.update(form);
      redirect.addFlashAttribute("messageHTML",
          MESSAGE.formatted("alert-success", "Success", "Product " + form.getSku() + " successfully updated"));
    }
    if (action.equals("add")) {
      productService.add(form);
      redirect.addFlashAttribute("messageHTML",
          MESSAGE.formatted("alert-success", "Success", "Product " + form.getSku() + " successfully added"));
    }

    if (action.equals("delete")) {
      productService.delete(form.getSku());
      redirect.addFlashAttribute("messageHTML",
          MESSAGE.formatted("alert-success", "Success", "Product " + form.getSku() + " successfully deleted"));
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
    redirect.addFlashAttribute("messageHTML", MESSAGE.formatted("alert-danger", "Error", exception.getMessage()));
    return "redirect:" + request.getServletPath();
  }

}
