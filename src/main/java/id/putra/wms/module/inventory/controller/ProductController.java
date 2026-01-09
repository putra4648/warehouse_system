package id.putra.wms.module.inventory.controller;

import java.util.List;
import java.util.Optional;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.putra.wms.module.inventory.service.core.ProductCoreService;
import id.putra.wms.shared.base.dto.ProductDto;
import id.putra.wms.shared.base.dto.response.ResponseData;
import id.putra.wms.shared.base.dto.response.ResponseMeta;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.exceptions.ModuleException;
import id.putra.wms.shared.helpers.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/master/product")
@Tag(name = "Product", description = "Product management APIs")
@SecurityRequirement(name = "oauth2")
public class ProductController {
    private final ResponseHelper responseHelper;
    private final ProductCoreService productCoreService;

    @PostMapping
    @Operation(summary = "Add new products", description = "Create one or more new product records")
    public ResponseEntity<ResponseData<String>> addProduct(@RequestBody @Valid List<ProductDto> body) {
        productCoreService.save(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @PatchMapping
    @Operation(summary = "Update products", description = "Update one or more existing product records")
    public ResponseEntity<ResponseData<String>> updateProduct(@RequestBody @Valid List<ProductDto> body) {
        productCoreService.update(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @DeleteMapping
    @Operation(summary = "Delete products", description = "Delete one or more product records by IDs")
    public ResponseEntity<ResponseData<String>> deleteProduct(
            @Parameter(description = "List of product IDs to delete") @RequestParam @Valid List<String> id) {
        productCoreService.delete(id.stream().map(i -> ProductDto.builder().id(i).build()).toList());
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @GetMapping
    @Operation(summary = "Get products", description = "Retrieve a paginated list of products with search functionality")
    public ResponseEntity<ResponseMeta<ProductDto>> getProducts(
            @Parameter(description = "Search term for product name") @RequestParam("search") String search,
            @ParameterObject @PageableDefault Pageable pageable) {
        var dto = ProductDto.builder().name(search).build();
        Page<ProductDto> products = productCoreService.getProducts(dto, pageable);
        return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, products);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product details", description = "Retrieve detailed information about a specific product")
    public ResponseEntity<ResponseData<ProductDto>> getDetailProduct(
            @Parameter(description = "Product ID") @PathVariable Optional<String> id) {
        var dto = ProductDto.builder()
                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                .build();
        ProductDto product = productCoreService.getProduct(dto);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, product);
    }

}
