package com.jpa.hak_kimheng_spring_data_jpa_homework.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
