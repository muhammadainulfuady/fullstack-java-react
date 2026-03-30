package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Stok;

public interface StokService {
    List<Stok> getAll();

    Stok getById(Long id);

    Stok create(Stok stok);

    Stok update(Long id, Stok stok);

    void delete(Long id);
}