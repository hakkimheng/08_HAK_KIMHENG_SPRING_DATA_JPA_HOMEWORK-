package com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response;

import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class OrderResponse {
    private Long orderId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private CustomerResponse customerResponse;
    private List<ProductResponse> productResponse;
}
