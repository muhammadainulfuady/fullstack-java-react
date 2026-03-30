package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Pengiriman;
import com.example.demo.service.PengirimanService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pengiriman")
public class PengirimanController {
    private final PengirimanService service;

    public PengirimanController(PengirimanService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Pengiriman>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Pengiriman> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Pengiriman> create(@Valid @RequestBody Pengiriman pengiriman) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(pengiriman));
    }

    @PutMapping("/{id}")
    public ApiResponse<Pengiriman> update(@PathVariable Long id, @Valid @RequestBody Pengiriman pengiriman) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, pengiriman));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}