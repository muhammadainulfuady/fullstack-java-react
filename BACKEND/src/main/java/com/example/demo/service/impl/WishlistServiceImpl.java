package com.example.demo.service.impl;

import com.example.demo.entity.Wishlist;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.WishlistRepository;
import com.example.demo.service.WishlistService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository repo;

    public WishlistServiceImpl(WishlistRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Wishlist> getAll() {
        return repo.findAll();
    }

    @Override
    public Wishlist getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Wishlist tidak ditemukan"));
    }

    @Override
    public Wishlist create(Wishlist wishlist) {
        return repo.save(wishlist);
    }

    @Override
    public Wishlist update(Long id, Wishlist wishlist) {
        Wishlist existing = getById(id);
        existing.setUser(wishlist.getUser());
        existing.setProduk(wishlist.getProduk());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}