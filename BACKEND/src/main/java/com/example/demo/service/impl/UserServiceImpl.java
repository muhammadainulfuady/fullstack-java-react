package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public User getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan"));
    }

    @Override
    public User create(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return repo.save(user);
    }

    @Override
    public User update(Long id, User user) {
        User existing = getById(id);
        existing.setNama(user.getNama());
        existing.setKota(user.getKota());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}