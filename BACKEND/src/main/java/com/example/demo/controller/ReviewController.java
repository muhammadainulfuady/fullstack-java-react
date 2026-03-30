package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Review;
import com.example.demo.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Review>> getAll() {
        return new ApiResponse<>(200, "Sukses", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Review> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @PostMapping
    public ApiResponse<Review> create(@Valid @RequestBody Review review) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(review));
    }

    @PutMapping("/{id}")
    public ApiResponse<Review> update(@PathVariable Long id, @Valid @RequestBody Review review) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, review));
    }

    @PatchMapping("/{id}")
    public ApiResponse<Review> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Review review = service.getById(id);
        if (fields.containsKey("komentar"))
            review.setKomentar((String) fields.get("komentar"));
        return new ApiResponse<>(200, "Sukses patch data", service.create(review));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}