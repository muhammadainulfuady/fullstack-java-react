package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<User>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<User> create(@Valid @RequestBody User user) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(user));
    }

    @PutMapping("/{id}")
    public ApiResponse<User> update(@PathVariable Long id, @Valid @RequestBody User user) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, user));
    }

    @PatchMapping("/{id}")
    public ApiResponse<User> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        User user = service.getById(id);
        if (fields.containsKey("nama"))
            user.setNama((String) fields.get("nama"));
        if (fields.containsKey("email"))
            user.setEmail((String) fields.get("email"));
        if (fields.containsKey("password"))
            user.setPassword((String) fields.get("password"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(user));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}