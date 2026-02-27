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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.putra.wms.module.inventory.dto.CategoryDto;
import id.putra.wms.module.inventory.service.core.CategoryCoreService;
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
@RequestMapping("/api/v1/master/category")
@Tag(name = "Category", description = "Product category management APIs")
@SecurityRequirement(name = "oauth2")
public class CategoryController {
    private final ResponseHelper responseHelper;
    private final CategoryCoreService categoryCoreService;

    @PostMapping
    @Operation(summary = "Add new categories", description = "Create one or more new category records")
    public ResponseEntity<ResponseData<String>> addCategory(@RequestBody @Valid List<CategoryDto> body) {
        categoryCoreService.save(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @PutMapping
    @Operation(summary = "Update categories", description = "Update one or more existing category records")
    public ResponseEntity<ResponseData<String>> updateCategory(@RequestBody @Valid List<CategoryDto> body) {
        categoryCoreService.update(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @DeleteMapping
    @Operation(summary = "Delete categories", description = "Delete one or more category records by IDs")
    public ResponseEntity<ResponseData<String>> deleteCategory(
            @Parameter(description = "List of category IDs to delete") @RequestParam @Valid List<Long> id) {
        categoryCoreService.delete(id.stream().map(i -> CategoryDto.builder().id(i).build()).toList());
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @GetMapping
    @Operation(summary = "Get categories", description = "Retrieve a paginated list of categories with search functionality")
    public ResponseEntity<ResponseMeta<CategoryDto>> getCategories(
            @Parameter(description = "Search term for category name") @RequestParam("search") String search,
            @ParameterObject @PageableDefault Pageable pageable) {
        var dto = CategoryDto.builder().name(search).build();
        Page<CategoryDto> categories = categoryCoreService.getCategories(dto, pageable);
        return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, categories);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category details", description = "Retrieve detailed information about a specific category")
    public ResponseEntity<ResponseData<CategoryDto>> getDetailCategory(
            @Parameter(description = "Category ID") @PathVariable Optional<Long> id) {
        var dto = CategoryDto.builder()
                .id(id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM)))
                .build();
        CategoryDto category = categoryCoreService.getCategory(dto);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, category);
    }

}
