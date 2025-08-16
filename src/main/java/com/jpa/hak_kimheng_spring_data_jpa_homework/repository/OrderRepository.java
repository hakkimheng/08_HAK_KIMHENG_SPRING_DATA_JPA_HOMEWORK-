package com.jpa.hak_kimheng_spring_data_jpa_homework.repository;

import com.jpa.hak_kimheng_spring_data_jpa_homework.enums.OrderStatus;
import com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
