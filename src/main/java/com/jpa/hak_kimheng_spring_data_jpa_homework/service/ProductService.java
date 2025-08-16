package com.jpa.hak_kimheng_spring_data_jpa_homework.service;

import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.ProductProperty;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.request.ProductRequest;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ListResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ProductResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity.Product;
import org.springframework.data.domain.Sort;

public interface ProductService {
    ListResponse<ProductResponse> getAllProduct(Integer page, Integer size, ProductProperty productProperty, Sort.Direction direction);

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    void deleteById(Long id);
}
