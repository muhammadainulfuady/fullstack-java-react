package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CartRequest;
import com.example.demo.entity.Keranjang;
import com.example.demo.entity.KeranjangItem;
import com.example.demo.service.KeranjangService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/keranjang")
public class KeranjangController {

    private final KeranjangService service;

    public KeranjangController(KeranjangService service) {
        this.service = service;
    }

    // ==========================================
    // 🛒 JALUR KHUSUS USER (BELANJA)
    // ==========================================

    // 1. User Memasukkan Barang ke Keranjang
    @PostMapping("/add")
    public ApiResponse<KeranjangItem> addToCart(@Valid @RequestBody CartRequest request) {
        // Ambil email user dari Token JWT yang sedang aktif
        String emailUser = SecurityContextHolder.getContext().getAuthentication().getName();

        // Panggil service untuk memasukkan barang
        KeranjangItem item = service.addToCart(emailUser, request);

        return new ApiResponse<>(201, "Berhasil memasukkan barang ke keranjang", item);
    }

    // 2. User Melihat Isi Keranjangnya Sendiri (URL diubah agar tidak bentrok)
    @GetMapping("/my-cart")
    public ApiResponse<List<KeranjangItem>> getMyCart() {
        // Ambil email user dari Token JWT yang sedang aktif
        String emailUser = SecurityContextHolder.getContext().getAuthentication().getName();

        // Panggil service untuk mengambil daftar barang
        List<KeranjangItem> cartItems = service.getCartItems(emailUser);

        return new ApiResponse<>(200, "Berhasil mengambil data keranjang", cartItems);
    }

    // ==========================================
    // 👑 JALUR ADMIN (BASIC CRUD BAWAANMU)
    // ==========================================

    @GetMapping
    public ApiResponse<List<Keranjang>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Keranjang> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Keranjang> create(@Valid @RequestBody Keranjang keranjang) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(keranjang));
    }

    @PutMapping("/{id}")
    public ApiResponse<Keranjang> update(@PathVariable Long id, @Valid @RequestBody Keranjang keranjang) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, keranjang));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}