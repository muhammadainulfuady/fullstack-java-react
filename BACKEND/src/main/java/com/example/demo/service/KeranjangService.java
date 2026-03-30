package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CartRequest;
import com.example.demo.entity.Keranjang;
import com.example.demo.entity.KeranjangItem;

public interface KeranjangService {
    // ==========================================
    // BASIC CRUD (Bawaan Kamu - Bisa untuk Admin)
    // ==========================================
    List<Keranjang> getAll();

    Keranjang getById(Long id);

    Keranjang create(Keranjang keranjang);

    Keranjang update(Long id, Keranjang keranjang);

    void delete(Long id);

    // ==========================================
    // BUSINESS LOGIC (Fungsi Utama E-Commerce)
    // ==========================================
    KeranjangItem addToCart(String emailUser, CartRequest request);

    List<KeranjangItem> getCartItems(String emailUser);
}