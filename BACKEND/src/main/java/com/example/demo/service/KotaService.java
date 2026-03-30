package com.example.demo.service;

import com.example.demo.entity.Kota;
import java.util.List;

public interface KotaService {
    List<Kota> getAll();

    Kota getById(Long id);

    Kota create(Kota kota);

    Kota update(Long id, Kota kota);

    void delete(Long id);
}