package com.example.demo.service.impl;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder; // <-- Import ini
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository repo;
    private final PasswordEncoder passwordEncoder;

    // 2. Inject PasswordEncoder ke dalam constructor
    public AdminServiceImpl(AdminRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Admin> getAll() {
        return repo.findAll();
    }

    @Override
    public Admin getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));
    }

    @Override
    public Admin create(Admin admin) {
        // 3. Enkripsi passwordAdmin sebelum disimpan ke database
        String hashedPassword = passwordEncoder.encode(admin.getPasswordAdmin());
        admin.setPasswordAdmin(hashedPassword);

        return repo.save(admin);
    }

    @Override
    public Admin update(Long id, Admin admin) {
        Admin existing = getById(id);
        existing.setNamaAdmin(admin.getNamaAdmin());
        existing.setEmailAdmin(admin.getEmailAdmin());

        // Opsional: Cek apakah user mengirim password baru saat update
        if (admin.getPasswordAdmin() != null && !admin.getPasswordAdmin().isEmpty()) {
            existing.setPasswordAdmin(passwordEncoder.encode(admin.getPasswordAdmin()));
        }

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}