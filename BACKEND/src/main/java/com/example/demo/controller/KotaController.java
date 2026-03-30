package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Kota;
import com.example.demo.service.KotaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kota")
public class KotaController {
    private final KotaService service;

    public KotaController(KotaService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Kota>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Kota> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Kota> create(@Valid @RequestBody Kota kota) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(kota));
    }

    @PutMapping("/{id}")
    public ApiResponse<Kota> update(@PathVariable Long id, @Valid @RequestBody Kota kota) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, kota));
    }

    @PatchMapping("/{id}")
    public ApiResponse<Kota> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Kota kota = service.getById(id);
        if (fields.containsKey("namaKota"))
            kota.setNamaKota((String) fields.get("namaKota"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(kota));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}