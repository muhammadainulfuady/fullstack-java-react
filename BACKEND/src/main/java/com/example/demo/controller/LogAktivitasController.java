package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.LogAktivitas;
import com.example.demo.service.LogAktivitasService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/log-aktivitas")
public class LogAktivitasController {
    private final LogAktivitasService service;

    public LogAktivitasController(LogAktivitasService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<LogAktivitas>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<LogAktivitas> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<LogAktivitas> create(@Valid @RequestBody LogAktivitas logAktivitas) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(logAktivitas));
    }

    @PutMapping("/{id}")
    public ApiResponse<LogAktivitas> update(@PathVariable Long id, @Valid @RequestBody LogAktivitas logAktivitas) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, logAktivitas));
    }

    @PatchMapping("/{id}")
    public ApiResponse<LogAktivitas> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        LogAktivitas log = service.getById(id);
        if (fields.containsKey("aktivitas"))
            log.setAktivitas((String) fields.get("aktivitas"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(log));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}