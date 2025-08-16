package com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "order_items")
public class OrderItem {
    @EmbeddedId
    @Builder.Default
    private OrderItemId id = new OrderItemId();
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
}
