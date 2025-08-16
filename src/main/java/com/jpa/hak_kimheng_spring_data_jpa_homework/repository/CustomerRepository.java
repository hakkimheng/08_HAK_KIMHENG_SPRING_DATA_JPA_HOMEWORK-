package com.jpa.hak_kimheng_spring_data_jpa_homework.repository;

import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.CustomerResponse;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
