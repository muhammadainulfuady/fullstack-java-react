package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Stok;
import com.example.demo.service.StokService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stok")
public class StokController {
    private final StokService service;

    public StokController(StokService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Stok>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Stok> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Stok> create(@Valid @RequestBody Stok stok) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(stok));
    }

    @PutMapping("/{id}")
    public ApiResponse<Stok> update(@PathVariable Long id, @Valid @RequestBody Stok stok) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, stok));
    }

    @PatchMapping("/{id}")
    public ApiResponse<Stok> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Stok stok = service.getById(id);
        if (fields.containsKey("jumlah"))
            stok.setJumlah((Integer) fields.get("jumlah"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(stok));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}