package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.OrderDetailService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order-detail")
public class OrderDetailController {
    private final OrderDetailService service;

    public OrderDetailController(OrderDetailService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<OrderDetail>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderDetail> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<OrderDetail> create(@Valid @RequestBody OrderDetail orderDetail) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(orderDetail));
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderDetail> update(@PathVariable Long id, @Valid @RequestBody OrderDetail orderDetail) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, orderDetail));
    }

    @PatchMapping("/{id}")
    public ApiResponse<OrderDetail> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        OrderDetail orderDetail = service.getById(id);
        if (fields.containsKey("qty"))
            orderDetail.setQty((Integer) fields.get("qty"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(orderDetail));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}