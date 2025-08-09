package id.putra.wms.module.inventory.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import id.putra.wms.shared.constants.MessageConstant;
import id.putra.wms.module.inventory.dto.request.ProductReq;
import id.putra.wms.shared.base.dto.param.SearchParam;
import id.putra.wms.shared.base.dto.response.thymeleaf.PagingResponse;
import id.putra.wms.module.inventory.service.core.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MasterProductController implements MasterDataController<ProductReq> {

  private final ProductService productService;

  @GetMapping("master/product")
  // @Override
  public String page(Model model, @RequestParam Optional<SearchParam> search) {
    model.addAttribute("productForm", new ProductReq());
    return "pages/master/product";
  }

  @PostMapping("master/product")
  @Override
  public String addOrUpdateProdut(
      @RequestParam String action,
      @Valid @ModelAttribute("form") ProductReq form,
      BindingResult result,
      RedirectAttributesModelMap redirect) {
    if (result.hasErrors()) {
      return "pages/master/product";
    }

    if (action.equals("edit")) {
      productService.update(form);
      redirect.addFlashAttribute("messageHTML",
          MessageConstant.MESSAGE.formatted("alert-success", "Success",
              "Product " + form.getSku() + " successfully updated"));
    }
    if (action.equals("add")) {
      productService.add(form);
      redirect.addFlashAttribute("messageHTML",
          MessageConstant.MESSAGE.formatted("alert-success", "Success",
              "Product " + form.getSku() + " successfully added"));
    }

    if (action.equals("delete")) {
      productService.delete(form.getSku());
      redirect.addFlashAttribute("messageHTML",
          MessageConstant.MESSAGE.formatted("alert-success", "Success",
              "Product " + form.getSku() + " successfully deleted"));
    }

    return "redirect:/master/product";
  }

  @PostMapping("api/master/product")
  @Override
  public ResponseEntity<PagingResponse<ProductReq>> getMasterData(@RequestBody SearchParam body) {
    // return ResponseEntity.ok().body(productService.getAll(body));
    return null;
  }

}
