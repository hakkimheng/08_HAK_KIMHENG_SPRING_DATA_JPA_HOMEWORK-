package com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity;

import com.jpa.hak_kimheng_spring_data_jpa_homework.model.dto.response.CustomerResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String address;
    @Column(name = "phone_number" , unique = true, nullable = false)
    String phoneNumber;

    @OneToOne(mappedBy = "customer" , cascade = CascadeType.ALL)
    private CustomerAccount customerAccount;

    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
    private List<Order> order;

    public CustomerResponse toCustomerResponse() {
        return CustomerResponse.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .phoneNumber(this.phoneNumber)
                .username(this.customerAccount.getUsername())
                .password(this.customerAccount.getPassword())
                .active(this.customerAccount.getIsActive())
                .build();
    }
}
