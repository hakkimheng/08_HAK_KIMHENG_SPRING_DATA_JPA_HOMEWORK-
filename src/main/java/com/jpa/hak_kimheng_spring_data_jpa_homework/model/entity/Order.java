package com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity;

import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.OrderStatus;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.CustomerResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.OrderResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ProductResponse;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "costomer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;


    public OrderResponse toOrderResponse() {
        List<ProductResponse> products = orderItems.stream()
                        .map(OrderItem::getProduct)
                        .map(Product::toProductResponse)
                        .toList();

        CustomerResponse customerResp =
                (customer == null) ? null : customer.toCustomerResponse();

        return OrderResponse.builder()
                .orderId(this.orderId)
                .orderDate(this.orderDate)
                .totalAmount(this.totalAmount)
                .status(this.status)
                .customerResponse(customerResp)
                .productResponse(products)
                .build();
    }
}
