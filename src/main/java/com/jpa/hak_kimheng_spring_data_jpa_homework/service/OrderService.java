package com.jpa.hak_kimheng_spring_data_jpa_homework.service;

import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.OrderProperty;
import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.OrderStatus;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.request.OrderRequest;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ListResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.OrderResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(Long customerId, List<OrderRequest> orderRequest);

    ListResponse<OrderResponse> getAllOrders(Integer page, Integer size, OrderProperty orderProperty, Sort.Direction direction , Long customerId);

    OrderResponse getOrderById(Long id);

    OrderResponse updateStatusOrder(OrderStatus orderStatus, Long orderId);

    void deleteOrderById(Long orderId);
}
