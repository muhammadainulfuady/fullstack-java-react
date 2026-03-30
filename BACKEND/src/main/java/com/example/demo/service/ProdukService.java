package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Produk;

public interface ProdukService {
    List<Produk> getAll();

    List<Produk> searchProduk(String keyword);

    Page<Produk> getAllPaginated(int page, int size, String sortBy, String sortDir);

    Produk getById(Long id);

    Produk create(Produk produk);

    Produk update(Long id, Produk produk);

    void delete(Long id);
}