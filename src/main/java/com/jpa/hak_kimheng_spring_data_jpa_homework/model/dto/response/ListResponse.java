package com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListResponse<T> {
    private List<T> items;
    private PaginationResponse pagination;
}
