package com.jpa.hak_kimheng_spring_data_jpa_homework.service.serviceImpl;

import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.ProductProperty;
import com.jpa.hak_kimheng_spring_data_jpa_homework.exception.NotFoundException;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.request.ProductRequest;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ListResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ProductResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity.Product;
import com.jpa.hak_kimheng_spring_data_jpa_homework.repository.ProductRepository;
import com.jpa.hak_kimheng_spring_data_jpa_homework.service.ProductService;
import com.jpa.hak_kimheng_spring_data_jpa_homework.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Helper helper;

    @Override
    public ListResponse<ProductResponse> getAllProduct(Integer page, Integer size, ProductProperty productProperty, Sort.Direction direction) {
        String field = productProperty.getFieldName();
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(direction, field));
        Page<Product> productResponse = productRepository.findAll(pageable);
        List<ProductResponse> productResponses = productResponse.getContent()
                .stream()
                .map(Product::toProductResponse)
                .toList();
        return ListResponse.<ProductResponse>builder()
                .items(productResponses)
                .pagination(helper.paginationToResponse(productResponse))
                .build();
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        return productRepository.save(productRequest.toEntity()).toProductResponse();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product id " + id +" Not Found!")
        ).toProductResponse();
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product id " + id +" Not Found!")
        );
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setUnitPrice(productRequest.getUnitPrice());
        return product.toProductResponse();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product id " + id +" Not Found!")
        );
        productRepository.deleteById(id);
    }

}
