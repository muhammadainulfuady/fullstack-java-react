package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Pembayaran;
import com.example.demo.service.PembayaranService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pembayaran")
public class PembayaranController {
    private final PembayaranService service;

    public PembayaranController(PembayaranService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Pembayaran>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Pembayaran> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Pembayaran> create(@Valid @RequestBody Pembayaran pembayaran) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(pembayaran));
    }

    @PutMapping("/{id}")
    public ApiResponse<Pembayaran> update(@PathVariable Long id, @Valid @RequestBody Pembayaran pembayaran) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, pembayaran));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}