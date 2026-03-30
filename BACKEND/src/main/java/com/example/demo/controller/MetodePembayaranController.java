package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.MetodePembayaran;
import com.example.demo.service.MetodePembayaranService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/metode-pembayaran")
public class MetodePembayaranController {
    private final MetodePembayaranService service;

    public MetodePembayaranController(MetodePembayaranService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<MetodePembayaran>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<MetodePembayaran> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<MetodePembayaran> create(@Valid @RequestBody MetodePembayaran metodePembayaran) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(metodePembayaran));
    }

    @PutMapping("/{id}")
    public ApiResponse<MetodePembayaran> update(@PathVariable Long id,
            @Valid @RequestBody MetodePembayaran metodePembayaran) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, metodePembayaran));
    }

    @PatchMapping("/{id}")
    public ApiResponse<MetodePembayaran> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        MetodePembayaran metode = service.getById(id);
        if (fields.containsKey("namaMetode"))
            metode.setNamaMetode((String) fields.get("namaMetode"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(metode));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}