package com.jpa.hak_kimheng_spring_data_jpa_homework.controller;

import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.ProductProperty;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.request.ProductRequest;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ApiResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.BaseResponseApi;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ListResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ProductResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity.Product;
import com.jpa.hak_kimheng_spring_data_jpa_homework.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
@Tag(name = "Product")
public class ProductController extends BaseResponseApi {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Get Pagination List of Product",
            description = "Retrieves a list of products in paginated format. You can specify the page number, page size, sorting property (from <code><span style='color:purple;'>ProductProperty</span></code> enum), and sort direction.")
    public ResponseEntity<ApiResponse<ListResponse<ProductResponse>>> getAllProducts(
            @RequestParam(defaultValue = "1") @Positive Integer page,
            @RequestParam(defaultValue = "10") @Positive Integer size,
            @RequestParam ProductProperty productProperty,
            @RequestParam Sort.Direction direction
    ) {
        return toResponse(productService.getAllProduct(page, size , productProperty, direction),
                "Product list retrieved successfully.",
                HttpStatus.CREATED
        );
    }

    @PostMapping
    @Operation(summary = "Create a New Product",
            description = "Creates a new product record in the system using the provided <code><span style='color:purple;'>ProductRequest</span></code> enum) payload. Returns the details of the newly created product.")
    public ResponseEntity<ApiResponse<ProductResponse>> getAllProducts(@RequestBody ProductRequest productRequest) {
        return toResponse(productService.createProduct(productRequest),
                "Customer has been created successfully.",
                HttpStatus.CREATED);
    }

    @GetMapping("/{product-id}")
    @Operation(summary = "Get product by Id",description = "Retrieves the details of a product using the specified product ID. ")
    public ResponseEntity<ApiResponse<ProductResponse>> getProduct(@PathVariable("product-id") Long id) {
        return toResponse(productService.getProductById(id),
                "Customer id  \" + id +\" retrieved successfully.",
                HttpStatus.OK);
    }

    @PutMapping("/{product-id}")
    @Operation(summary = "Update Product by Id",
            description = "Updates the details of an existing product using the provided product ID and <code><span style='color:purple;'>ProductRequest</span></code> payload")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable("product-id") Long id , @RequestBody ProductRequest productRequest) {
        return toResponse(productService.updateProduct(id , productRequest),
                "Product id "+id + " has been updated successfully",
                HttpStatus.OK);
    }

    @DeleteMapping("/{product-id}")
    @Operation(summary = "Delete Product By Id",
    description = "Deletes an existing product from the system using the specified product ID. " )
    public ResponseEntity<ApiResponse<ProductResponse>> deleteProduct(@PathVariable("product-id") Long id) {
        productService.deleteById(id);
        return toResponse(null,
                "Product has been deleted successfully",
                HttpStatus.OK);
    }
}
