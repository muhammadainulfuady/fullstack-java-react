package com.example.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private int statusCode;
    private String message;
    private T payload;
}