package id.putra.wms.module.customer.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.putra.wms.module.customer.dto.CustomerDto;
import id.putra.wms.module.customer.service.core.CustomerService;
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
@RequestMapping("/api/v1/master/customer")
@Tag(name = "Customer", description = "Customer management APIs")
@SecurityRequirement(name = "oauth2")
public class CustomerController {
    private final ResponseHelper responseHelper;
    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Add new customers", description = "Create new customer records")
    public ResponseEntity<ResponseData<List<CustomerDto>>> addCustomer(@RequestBody @Valid List<CustomerDto> body) {
        List<CustomerDto> createdCustomers = customerService.create(body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, createdCustomers);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Update customer", description = "Update an existing customer record")
    public ResponseEntity<ResponseData<CustomerDto>> updateCustomer(
            @Parameter(description = "Customer ID") @PathVariable Long id,
            @RequestBody @Valid CustomerDto body) {
        CustomerDto updatedCustomer = customerService.update(id, body);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer", description = "Delete a customer record by ID")
    public ResponseEntity<ResponseData<String>> deleteCustomer(
            @Parameter(description = "Customer ID to delete") @PathVariable Long id) {
        customerService.delete(id);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, "SUCCESS");
    }

    @GetMapping
    @Operation(summary = "Get customers", description = "Retrieve a paginated list of customers with search functionality")
    public ResponseEntity<ResponseMeta<CustomerDto>> getCustomers(
            @Parameter(description = "Search term for customer name") @RequestParam(value = "search", defaultValue = "") String search,
            @ParameterObject @PageableDefault Pageable pageable) {
        Page<CustomerDto> customers = customerService.getAll(search, pageable);
        return responseHelper.createResponseMeta(ResponseEnum.SUCCESS, customers);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer details", description = "Retrieve detailed information about a specific customer")
    public ResponseEntity<ResponseData<CustomerDto>> getDetailCustomer(
            @Parameter(description = "Customer ID") @PathVariable Optional<Long> id) {
        Long customerId = id.orElseThrow(() -> new ModuleException(ResponseEnum.INVALID_PARAM));
        CustomerDto customer = customerService.getById(customerId);
        return responseHelper.createResponseData(ResponseEnum.SUCCESS, customer);
    }
}
