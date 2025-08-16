package com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class BaseResponseApi {
    public <T> ResponseEntity<ApiResponse<T>> toResponse(T payload , String  message , HttpStatus status) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .message(message)
                .payload(payload)
                .status(status)
                .build();
    return ResponseEntity.status(status).body(response);
    }
}
