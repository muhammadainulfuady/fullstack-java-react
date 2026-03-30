package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.MetodePembayaran;

public interface MetodePembayaranService {
    List<MetodePembayaran> getAll();

    MetodePembayaran getById(Long id);

    MetodePembayaran create(MetodePembayaran metodePembayaran);

    MetodePembayaran update(Long id, MetodePembayaran metodePembayaran);

    void delete(Long id);
}