package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.GambarProduk;
import com.example.demo.service.GambarProdukService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gambar-produk")
public class GambarProdukController {
    private final GambarProdukService service;

    public GambarProdukController(GambarProdukService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<GambarProduk>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<GambarProduk> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<GambarProduk> create(@Valid @RequestBody GambarProduk gambarProduk) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(gambarProduk));
    }

    @PutMapping("/{id}")
    public ApiResponse<GambarProduk> update(@PathVariable Long id, @Valid @RequestBody GambarProduk gambarProduk) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, gambarProduk));
    }

    @PatchMapping("/{id}")
    public ApiResponse<GambarProduk> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        GambarProduk gambarProduk = service.getById(id);
        if (fields.containsKey("url"))
            gambarProduk.setUrl((String) fields.get("url"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(gambarProduk));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}