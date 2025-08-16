package com.jpa.hak_kimheng_spring_data_jpa_homework.enums;

import lombok.Getter;

@Getter
public enum ProductProperty {
    ID("productId"),
    NAME("name")
    ,UNIT_PRICE("unitPrice");
    private final String fieldName;
    ProductProperty(String fieldName) {
        this.fieldName = fieldName;
    }
}
