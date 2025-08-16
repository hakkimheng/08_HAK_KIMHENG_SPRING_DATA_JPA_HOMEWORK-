package com.jpa.hak_kimheng_spring_data_jpa_homework.repository;

import com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
