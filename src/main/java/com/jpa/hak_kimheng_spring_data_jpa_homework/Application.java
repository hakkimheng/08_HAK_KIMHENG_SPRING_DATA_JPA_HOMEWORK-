package com.jpa.hak_kimheng_spring_data_jpa_homework;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Homework Spring Data JPA",
        description = "This API provides endpoints for managing customers, products, and orders using Spring Data JPA."
))
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
