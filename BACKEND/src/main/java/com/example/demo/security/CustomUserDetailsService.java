package com.example.demo.security;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository; // Tambahkan AdminRepository

    public CustomUserDetailsService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Cek apakah email ini milik User biasa
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Berikan pangkat ROLE_USER
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        }

        // 2. Jika bukan User, cek apakah email ini milik Admin
        Optional<Admin> adminOpt = adminRepository.findByEmailAdmin(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            // Berikan pangkat ROLE_ADMIN
            return new org.springframework.security.core.userdetails.User(
                    admin.getEmailAdmin(),
                    admin.getPasswordAdmin(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }

        // 3. Jika di kedua tabel tidak ada, tolak loginnya
        throw new UsernameNotFoundException("Email tidak ditemukan di sistem");
    }
}