package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.KeranjangItem;
import com.example.demo.service.KeranjangItemService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/keranjang-item")
public class KeranjangItemController {
    private final KeranjangItemService service;

    public KeranjangItemController(KeranjangItemService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<KeranjangItem>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<KeranjangItem> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<KeranjangItem> create(@Valid @RequestBody KeranjangItem keranjangItem) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(keranjangItem));
    }

    @PutMapping("/{id}")
    public ApiResponse<KeranjangItem> update(@PathVariable Long id, @Valid @RequestBody KeranjangItem keranjangItem) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, keranjangItem));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}