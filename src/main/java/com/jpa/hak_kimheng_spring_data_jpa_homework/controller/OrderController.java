package com.jpa.hak_kimheng_spring_data_jpa_homework.controller;

import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.OrderProperty;
import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.OrderStatus;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.request.OrderRequest;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ApiResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.BaseResponseApi;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ListResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.OrderResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.service.OrderService;
import com.jpa.hak_kimheng_spring_data_jpa_homework.utils.Helper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
@Tag(name = "Order")
public class OrderController extends BaseResponseApi {

    private final OrderService orderService;

    @PostMapping("/{customer-id}")
    @Operation(summary = "Create order(s) for a customer",
            description = "Creates one new order for the given customer using a non-empty list of <code><span style='color:purple;'>OrderRequest</span></code> items (each item represents an order line). Returns the created order with calculated totals.)" )
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@PathVariable("customer-id")  Long customerId,
                                                                      @RequestBody List<OrderRequest> orderRequest) {
        return toResponse(orderService.createOrder(customerId , orderRequest),
        "Order has been created successfully. ",
                HttpStatus.CREATED);
    }

    @GetMapping("/lists/{customer-id}")
    @Operation(summary = "List order by customer (pagination)",
            description = "Retrieves a paginated list of orders for the specified customer. Supports page/size and sorting by <code><span style='color:purple;'>OrderProperty</span></code> with <code><span style='color:purple;'>Sort.Direction</span></code>" )
    public ResponseEntity<ApiResponse<ListResponse<OrderResponse>>> getAllOrders(
                                                                    @PathVariable("customer-id") @Positive Long customerId,
                                                                    @RequestParam(defaultValue = "1") @Positive Integer page,
                                                                    @RequestParam(defaultValue = "10") @Positive Integer size,
                                                                    @RequestParam OrderProperty  orderProperty,
                                                                    @RequestParam Sort.Direction direction                                                                  ) {
        return toResponse(orderService.getAllOrders(page , size , orderProperty , direction, customerId),
                "Product list retrieved successfully.",
                HttpStatus.OK);
    }

    @GetMapping("/{order-id}")
    @Operation(summary = "Get order details by ID",
            description = "Fetches a single order, including its line items and status, using the provided order ID. ")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable("order-id") Long id) {
        return toResponse(orderService.getOrderById(id),
                "Customer id  " + id +" retrieved successfully.",
                HttpStatus.OK);
    }

    @PutMapping("/{order-id}/status")
    @Operation(summary = "Update order status",
            description = "Updates the status of an existing order using the provided order ID and <code><span style='color:purple;'>OrderStatus</span></code> value (e.g.,<code><span style='color:purple;'>PENDING</span></code> , <code><span style='color:purple;'>PAID</span></code> , <code><span style='color:purple;'>SHIPPED</span></code> , <code><span style='color:purple;'>CANCELLED</span></code>" )
    public ResponseEntity<ApiResponse<OrderResponse>> updateStatusOrder(@RequestParam OrderStatus orderStatus, @PathVariable("order-id")  Long orderId) {

        return toResponse(orderService.updateStatusOrder(orderStatus , orderId),
                "Order status has been updated successfully.",
                HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    @Operation(summary = "Delete order by Id", description = "Deletes an order from the system using the specified order ID. ")
    public ResponseEntity<ApiResponse<OrderResponse>> deleteOrderById(@PathVariable("order-id") Long orderId) {
        orderService.deleteOrderById(orderId);
        return toResponse(null,
                "Order has been deleted successful",
                HttpStatus.OK);
    }

}
