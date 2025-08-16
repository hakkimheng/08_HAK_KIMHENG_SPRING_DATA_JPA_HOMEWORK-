package com.jpa.hak_kimheng_spring_data_jpa_homework.service;

import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.CustomerProperty;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.request.CustomerRequest;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ListResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.CustomerResponse;
import org.springframework.data.domain.Sort;

public interface CustomerService {
    ListResponse<CustomerResponse> getAllCustomers(Integer pageNumber, Integer pageSize , CustomerProperty customerProperty , Sort.Direction direction);

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateCustomerById(Long id, CustomerRequest customerRequest);

    void deleteCustomer(Long id);
}
