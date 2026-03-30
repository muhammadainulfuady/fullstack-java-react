package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils) {
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@Valid @RequestBody LoginRequest request) {
        try {
            // Cek ke database apakah email dan password cocok
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            // Jika cocok, buatkan Token JWT
            String token = jwtUtils.generateToken(request.getEmail());
            return new ApiResponse<>(200, "Login Berhasil", token);

        } catch (Exception e) {
            return new ApiResponse<>(401, "Email atau Password salah!", null);
        }
    }
}