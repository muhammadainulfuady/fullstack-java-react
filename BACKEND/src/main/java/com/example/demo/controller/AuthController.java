package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Menambahkan UserRepository dan PasswordEncoder ke dalam Constructor
    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils, UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ApiResponse<Map<String, String>> login(@Valid @RequestBody LoginRequest request) {
        try {
            // Cek ke database apakah email dan password cocok
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            // Jika cocok, buatkan Token JWT
            String token = jwtUtils.generateToken(request.getEmail());

            // Bungkus token dalam Map agar format JSON-nya rapi dan terbaca oleh React
            return new ApiResponse<>(200, "Login Berhasil", Map.of("token", token));

        } catch (Exception e) {
            return new ApiResponse<>(401, "Email atau Password salah!", null);
        }
    }

    @PostMapping("/register")
    public ApiResponse<?> register(@RequestBody User user) {
        // 1. Cek apakah email sudah terdaftar di database
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return new ApiResponse<>(400, "Registrasi Gagal: Email sudah digunakan!", null);
        }

        // 2. Enkripsi (Bcrypt) password sebelum disimpan ke database
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 3. Simpan User baru ke database
        userRepository.save(user);

        return new ApiResponse<>(201, "Registrasi Berhasil! Silakan Login.", null);
    }
}