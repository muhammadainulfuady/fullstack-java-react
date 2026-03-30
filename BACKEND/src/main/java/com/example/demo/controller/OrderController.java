package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // ==========================================
    // 🛒 JALUR KHUSUS USER (BELANJA)
    // ==========================================

    @PostMapping("/checkout")
    public ApiResponse<Order> checkout() {
        String emailUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Order order = service.checkout(emailUser);
        return new ApiResponse<>(201, "Checkout Berhasil!", order);
    }

    @GetMapping("/my-orders")
    public ApiResponse<List<Order>> getMyOrders() {
        String emailUser = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> orders = service.getMyOrders(emailUser);
        return new ApiResponse<>(200, "Berhasil mengambil riwayat pesanan", orders);
    }

    // ==========================================
    // 👑 JALUR ADMIN (BASIC CRUD)
    // ==========================================

    @GetMapping
    public ApiResponse<List<Order>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Order> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Order> create(@Valid @RequestBody Order order) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(order));
    }

    @PutMapping("/{id}")
    public ApiResponse<Order> update(@PathVariable Long id, @Valid @RequestBody Order order) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, order));
    }

    @PatchMapping("/{id}")
    public ApiResponse<Order> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Order order = service.getById(id);
        if (fields.containsKey("total")) {
            order.setTotal(Double.valueOf(fields.get("total").toString()));
        }
        return new ApiResponse<>(200, "Sukses patch data", service.update(id, order)); // Ganti service.create ke
                                                                                       // service.update agar rapi
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}