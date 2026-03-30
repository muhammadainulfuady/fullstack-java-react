package com.example.demo.service;

import com.example.demo.entity.Pengiriman;
import java.util.List;

public interface PengirimanService {
    List<Pengiriman> getAll();

    Pengiriman getById(Long id);

    Pengiriman create(Pengiriman pengiriman);

    Pengiriman update(Long id, Pengiriman pengiriman);

    void delete(Long id);
}