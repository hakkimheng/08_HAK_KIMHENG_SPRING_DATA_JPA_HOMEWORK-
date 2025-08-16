package com.jpa.hak_kimheng_spring_data_jpa_homework.enums;

import lombok.Getter;

@Getter
public enum OrderProperty {
        ID("orderId"),
        ORDER_DATE("orderDate"),
        TOTAL_AMOUNT("totalAmount");

        private final String fieldName;

        OrderProperty(String fieldName) {
            this.fieldName = fieldName;
        }

}
