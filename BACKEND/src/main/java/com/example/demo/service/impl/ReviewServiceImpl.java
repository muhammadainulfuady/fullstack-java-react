package com.example.demo.service.impl;

import com.example.demo.entity.Review;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.service.ReviewService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repo;

    public ReviewServiceImpl(ReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Review> getAll() {
        return repo.findAll();
    }

    @Override
    public Review getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review tidak ditemukan"));
    }

    @Override
    public Review create(Review review) {
        return repo.save(review);
    }

    @Override
    public Review update(Long id, Review review) {
        Review existing = getById(id);
        existing.setKomentar(review.getKomentar());
        existing.setProduk(review.getProduk());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}