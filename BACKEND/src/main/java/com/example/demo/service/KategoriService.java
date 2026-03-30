package com.example.demo.service;

import com.example.demo.entity.Kategori;
import java.util.List;

public interface KategoriService {
    List<Kategori> getAll();

    Kategori getById(Long id);

    Kategori create(Kategori kategori);

    Kategori update(Long id, Kategori kategori);

    void delete(Long id);
}