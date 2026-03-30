package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Pembayaran;

public interface PembayaranService {
    List<Pembayaran> getAll();

    Pembayaran getById(Long id);

    Pembayaran create(Pembayaran pembayaran);

    Pembayaran update(Long id, Pembayaran pembayaran);

    void delete(Long id);
}