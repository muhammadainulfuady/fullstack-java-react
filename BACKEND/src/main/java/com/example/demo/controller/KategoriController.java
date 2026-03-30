package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Kategori;
import com.example.demo.service.KategoriService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kategori")
public class KategoriController {
    private final KategoriService service;

    public KategoriController(KategoriService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Kategori>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Kategori> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Kategori> create(@Valid @RequestBody Kategori kategori) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(kategori));
    }

    @PutMapping("/{id}")
    public ApiResponse<Kategori> update(@PathVariable Long id, @Valid @RequestBody Kategori kategori) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, kategori));
    }

    @PatchMapping("/{id}")
    public ApiResponse<Kategori> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Kategori kategori = service.getById(id);
        if (fields.containsKey("namaKategori"))
            kategori.setNamaKategori((String) fields.get("namaKategori"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(kategori));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}