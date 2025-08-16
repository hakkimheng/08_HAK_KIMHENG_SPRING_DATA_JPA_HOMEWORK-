package com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.request;

import com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Unit price is required")
    @DecimalMin(value = "0.01", message = "Unit price must be greater than 0")
    private BigDecimal unitPrice;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;


    public Product toEntity() {
        return Product.builder()
                .name(this.name)
                .unitPrice(this.unitPrice)
                .description(this.description)
                .build();
    }
}
