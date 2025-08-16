package com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String username;
    private String password;
    private Boolean active;
}
