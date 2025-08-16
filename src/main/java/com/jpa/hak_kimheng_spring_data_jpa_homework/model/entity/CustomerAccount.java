package com.jpa.hak_kimheng_spring_data_jpa_homework.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer_accounts")
public class CustomerAccount {
    @Id
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(name = "is_active" , nullable = false)
    private Boolean isActive;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Customer customer;
}
