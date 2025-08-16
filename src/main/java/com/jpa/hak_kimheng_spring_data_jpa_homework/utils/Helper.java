package com.jpa.hak_kimheng_spring_data_jpa_homework.utils;

import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.PaginationResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

@Configuration
public class Helper {
    public <T> PaginationResponse paginationToResponse(Page<T> page) {
        return PaginationResponse.builder()
                .currentPage(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .pageSize(page.getSize())
                .build();
    }
}
