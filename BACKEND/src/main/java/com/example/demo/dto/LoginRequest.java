package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Format email salah")
    private String email;

    @NotBlank(message = "Password tidak boleh kosong")
    private String password;
}