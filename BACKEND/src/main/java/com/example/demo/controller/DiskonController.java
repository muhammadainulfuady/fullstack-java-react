package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Diskon;
import com.example.demo.service.DiskonService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diskon")
public class DiskonController {
    private final DiskonService service;

    public DiskonController(DiskonService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Diskon>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Diskon> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Diskon> create(@Valid @RequestBody Diskon diskon) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(diskon));
    }

    @PutMapping("/{id}")
    public ApiResponse<Diskon> update(@PathVariable Long id, @Valid @RequestBody Diskon diskon) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, diskon));
    }

    @PatchMapping("/{id}")
    public ApiResponse<Diskon> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Diskon diskon = service.getById(id);
        if (fields.containsKey("persen"))
            diskon.setPersen((Integer) fields.get("persen"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(diskon));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}