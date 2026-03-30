package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Wishlist;
import com.example.demo.service.WishlistService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    private final WishlistService service;

    public WishlistController(WishlistService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Wishlist>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Wishlist> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Wishlist> create(@Valid @RequestBody Wishlist wishlist) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(wishlist));
    }

    @PutMapping("/{id}")
    public ApiResponse<Wishlist> update(@PathVariable Long id, @Valid @RequestBody Wishlist wishlist) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, wishlist));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}