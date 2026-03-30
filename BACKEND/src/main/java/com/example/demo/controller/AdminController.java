package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Admin>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Admin> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Admin> create(@Valid @RequestBody Admin admin) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(admin));
    }

    @PutMapping("/{id}")
    public ApiResponse<Admin> update(@PathVariable Long id, @Valid @RequestBody Admin admin) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, admin));
    }

    @PatchMapping("/{id}")
    public ApiResponse<Admin> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Admin admin = service.getById(id);
        if (fields.containsKey("namaAdmin"))
            admin.setNamaAdmin((String) fields.get("namaAdmin"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(admin));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}