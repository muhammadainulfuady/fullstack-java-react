package com.example.demo.service;

import com.example.demo.entity.GambarProduk;
import java.util.List;

public interface GambarProdukService {
    List<GambarProduk> getAll();

    GambarProduk getById(Long id);

    GambarProduk create(GambarProduk gambarProduk);

    GambarProduk update(Long id, GambarProduk gambarProduk);

    void delete(Long id);
}