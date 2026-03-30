package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Kurir;
import com.example.demo.service.KurirService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kurir")
public class KurirController {
    private final KurirService service;

    public KurirController(KurirService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Kurir>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Kurir> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Kurir> create(@Valid @RequestBody Kurir kurir) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(kurir));
    }

    @PutMapping("/{id}")
    public ApiResponse<Kurir> update(@PathVariable Long id, @Valid @RequestBody Kurir kurir) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, kurir));
    }

    @PatchMapping("/{id}")
    public ApiResponse<Kurir> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Kurir kurir = service.getById(id);
        if (fields.containsKey("namaKurir"))
            kurir.setNamaKurir((String) fields.get("namaKurir"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(kurir));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}