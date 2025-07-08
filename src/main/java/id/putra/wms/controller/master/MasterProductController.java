package id.putra.wms.controller.master;

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

import id.putra.wms.constant.MessageConstant;
import id.putra.wms.dto.ProductDto;
import id.putra.wms.dto.param.SearchParam;
import id.putra.wms.dto.response.PagingResponse;
import id.putra.wms.service.impl.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MasterProductController implements MasterDataController<ProductDto> {

  private final ProductService productService;

  @GetMapping("master/product")
  @Override
  public String page(Model model) {
    model.addAttribute("productForm", new ProductDto());
    return "pages/master/product";
  }

  @PostMapping("master/product")
  @Override
  public String addOrUpdateProdut(
      @RequestParam String action,
      @Valid @ModelAttribute("form") ProductDto form,
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
  public ResponseEntity<PagingResponse<ProductDto>> getMasterData(@RequestBody SearchParam body) {
    return ResponseEntity.ok().body(productService.getAll(body));
  }

}
