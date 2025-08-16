package com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity;

import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ProductResponse;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    private String name;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    private String description;

    public ProductResponse toProductResponse() {
        return ProductResponse.builder()
                .id(this.productId)
                .name(this.name)
                .description(this.description)
                .unitPrice(this.unitPrice)
                .build();
    }
}
