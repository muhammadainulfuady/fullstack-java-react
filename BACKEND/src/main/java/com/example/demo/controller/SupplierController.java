package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Supplier;
import com.example.demo.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Supplier>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Supplier> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Supplier> create(@Valid @RequestBody Supplier supplier) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(supplier));
    }

    @PutMapping("/{id}")
    public ApiResponse<Supplier> update(@PathVariable Long id, @Valid @RequestBody Supplier supplier) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, supplier));
    }

    @PatchMapping("/{id}")
    public ApiResponse<Supplier> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Supplier supplier = service.getById(id);
        if (fields.containsKey("namaSupplier"))
            supplier.setNamaSupplier((String) fields.get("namaSupplier"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(supplier));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}