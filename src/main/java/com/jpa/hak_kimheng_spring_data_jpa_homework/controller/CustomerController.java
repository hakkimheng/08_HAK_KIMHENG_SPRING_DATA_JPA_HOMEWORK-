package com.jpa.hak_kimheng_spring_data_jpa_homework.controller;

import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.CustomerProperty;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.request.CustomerRequest;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ApiResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.BaseResponseApi;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ListResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.CustomerResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
@Tag(name = "Customer")
public class CustomerController extends BaseResponseApi {

    private final CustomerService customerService;

    @GetMapping
    @Operation(summary = "Get Paginated List of Customers" ,description = "Retrieves all customers in a paginated format. You can specify the page number, page size, sorting property, and sort direction.")
    public ResponseEntity<ApiResponse<ListResponse<CustomerResponse>>> getAllCustomers(
                                                                     @RequestParam(defaultValue = "1") @Positive Integer page,
                                                                     @RequestParam(defaultValue = "10") @Positive Integer size,
                                                                     CustomerProperty customerProperty,
                                                                     Sort.Direction direction) {
        return toResponse(customerService.getAllCustomers(page , size , customerProperty , direction),
                "Customer list retrieved successfully.", HttpStatus.CREATED);
    }

    @PostMapping
    @Operation(summary = "create a customer",
            description = "Accepts a valid  <code><span style='color:purple;'>CustomerRequest</span></code> object to create a new customer record. Returns the newly created customer's details." )
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return toResponse(customerService.createCustomer(customerRequest) ,
                "Customer has been created successfully.",
                HttpStatus.OK);
    }

    @GetMapping("/{customer-id}")
    @Operation(summary = "Get customer by ID" , description = "Fetches detailed information about a single customer using the provided customer ID.")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomer(@PathVariable("customer-id") Long id) {
        return toResponse(customerService.getCustomerById(id),
                "Customer id  " + id +" retrieved successfully.",
                HttpStatus.OK
        );
    }

    @PutMapping("/{customer-id}")
    @Operation(summary = "Update exits customer",description = "Updates the details of an existing customer based on the provided customer ID and request body.")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(@PathVariable("customer-id") Long id ,@RequestBody CustomerRequest customerRequest) {
        return toResponse(customerService.updateCustomerById(id , customerRequest),
                "Customer id " + id + "has been updated successfully",
                HttpStatus.OK);
    }

    @DeleteMapping("/{customer-id}")
    @Operation(summary = "Delete Customer" , description = "Removes a customer record from the system based on the provided customer ID.")
    public ResponseEntity<ApiResponse<CustomerResponse>> deleteCustomer(@PathVariable("customer-id") Long id) {
        customerService.deleteCustomer(id);
        return toResponse(null,
                "Customer has been deleted successfully",
                HttpStatus.OK);
    }

}
