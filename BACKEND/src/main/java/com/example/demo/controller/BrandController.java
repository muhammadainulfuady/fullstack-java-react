package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Brand;
import com.example.demo.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Brand>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Brand> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Brand> create(@Valid @RequestBody Brand brand) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(brand));
    }

    @PutMapping("/{id}")
    public ApiResponse<Brand> update(@PathVariable Long id, @Valid @RequestBody Brand brand) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, brand));
    }

    @PatchMapping("/{id}")
    public ApiResponse<Brand> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Brand brand = service.getById(id);
        if (fields.containsKey("namaBrand"))
            brand.setNamaBrand((String) fields.get("namaBrand"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(brand));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}