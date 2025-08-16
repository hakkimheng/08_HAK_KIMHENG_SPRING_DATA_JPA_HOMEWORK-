package com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderItemId implements Serializable {
    private Long orderId;
    private Long productId;
}
