package com.jpa.hak_kimheng_spring_data_jpa_homework.service.serviceImpl;

import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.OrderProperty;
import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.OrderStatus;
import com.jpa.hak_kimheng_spring_data_jpa_homework.exception.NotFoundException;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.request.OrderRequest;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ListResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.OrderResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity.*;
import com.jpa.hak_kimheng_spring_data_jpa_homework.repository.CustomerRepository;
import com.jpa.hak_kimheng_spring_data_jpa_homework.repository.OrderRepository;
import com.jpa.hak_kimheng_spring_data_jpa_homework.repository.ProductRepository;
import com.jpa.hak_kimheng_spring_data_jpa_homework.service.OrderService;
import com.jpa.hak_kimheng_spring_data_jpa_homework.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final Helper helper;


    @Override
    public OrderResponse createOrder(Long customerId, List<OrderRequest> orderRequestItem) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer " + customerId + " not found."));

        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .customer(customer)
                .status(OrderStatus.PENDING)
                .orderItems(new ArrayList<>())
                .build();

        BigDecimal total = BigDecimal.ZERO;

        for (OrderRequest req : orderRequestItem) {
            Product product = productRepository.findById(req.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product " + req.getProductId() + " not found."));

            BigDecimal line = product.getUnitPrice().multiply(BigDecimal.valueOf(req.getQuantity()));
            total = total.add(line);
            OrderItem item = OrderItem.builder()
                    .product(product)
                    .quantity(req.getQuantity())
                    .build();
            item.setOrder(order);
            order.getOrderItems().add(item);
        }
        order.setTotalAmount(total);
        return  orderRepository.save(order).toOrderResponse();
    }

    @Override
    public ListResponse<OrderResponse> getAllOrders(Integer page, Integer size, OrderProperty orderProperty, Sort.Direction direction, Long customerId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, orderProperty.getFieldName()));
        Page<Order> orders = orderRepository.findAll(pageable);
        List<OrderResponse> orderResponseList = orders.getContent()
                .stream()
                .filter(order ->
                   order.getCustomer().getId().equals(customerId))
                .map(Order::toOrderResponse)
                .toList();
        return ListResponse.<OrderResponse>builder()
                .items(orderResponseList)
                .pagination(helper.paginationToResponse(orders))
                .build();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order id " + id + "Not Found!")).toOrderResponse();
    }

    @Override
    public OrderResponse updateStatusOrder(OrderStatus orderStatus, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("Order id " + orderId + "Not Found!")
        );
        order.setStatus(orderStatus);
        return orderRepository.save(order).toOrderResponse();
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("Order id " + orderId + "Not Found!")
        );
        orderRepository.deleteById(orderId);
    }


}
