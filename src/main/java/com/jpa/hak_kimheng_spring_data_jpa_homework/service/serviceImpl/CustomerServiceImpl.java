package com.jpa.hak_kimheng_spring_data_jpa_homework.service.serviceImpl;

import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.CustomerProperty;
import com.jpa.hak_kimheng_spring_data_jpa_homework.exception.NotFoundException;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.request.CustomerRequest;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.ListResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.CustomerResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity.Customer;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity.CustomerAccount;
import com.jpa.hak_kimheng_spring_data_jpa_homework.repository.CustomerRepository;
import com.jpa.hak_kimheng_spring_data_jpa_homework.service.CustomerService;
import com.jpa.hak_kimheng_spring_data_jpa_homework.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Helper helper;

    @Override
    public ListResponse<CustomerResponse> getAllCustomers(Integer pageNumber, Integer pageSize, CustomerProperty customerProperty, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(direction, customerProperty.toString().toLowerCase(Locale.ROOT)));
        Page<Customer> customerResponse = Optional.of(customerRepository.findAll(pageable)).orElseThrow(
                () -> new NotFoundException("Customer not found!")
        );

        List<CustomerResponse> customerResponses =
                customerResponse.getContent()
                        .stream()
                        .map(Customer::toCustomerResponse)
                        .toList();

        return ListResponse.<CustomerResponse>builder()
                .items(customerResponses)
                .pagination(helper.paginationToResponse(customerResponse))
                .build();
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        CustomerAccount  customerAccount = CustomerAccount.builder()
                .username(customerRequest.getUsername())
                .password(customerRequest.getPassword())
                .isActive(true)
                .build();
         Customer customer = Customer.builder()
                 .name(customerRequest.getName())
                 .phoneNumber(customerRequest.getPhoneNumber())
                 .address(customerRequest.getAddress())
                 .build();
         customer.setCustomerAccount(customerAccount);
         customerAccount.setCustomer(customer);
        return customerRepository.save(customer).toCustomerResponse();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Customer id " + id + " Not Found!")
        ).toCustomerResponse();
    }

    @Override
    public CustomerResponse updateCustomerById(Long id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new NotFoundException("Customer id " + id + " Not Found!"));
        CustomerAccount customerAccount = CustomerAccount.builder()
                .username(customerRequest.getUsername())
                .password(customerRequest.getPassword())
                .build();
        customer.setName(customerRequest.getName());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setAddress(customerRequest.getAddress());
        customer.setCustomerAccount(customerAccount);
        return customerRepository.save(customer).toCustomerResponse();
    }

    @Override
    public void deleteCustomer(Long id) {
         customerRepository.deleteById(id);
    }
}
