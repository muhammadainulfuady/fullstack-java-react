package com.example.demo.service;

import com.example.demo.entity.Wishlist;
import java.util.List;

public interface WishlistService {
    List<Wishlist> getAll();

    Wishlist getById(Long id);

    Wishlist create(Wishlist wishlist);

    Wishlist update(Long id, Wishlist wishlist);

    void delete(Long id);
}