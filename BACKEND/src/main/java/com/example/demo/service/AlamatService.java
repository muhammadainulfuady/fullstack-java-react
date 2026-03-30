package com.example.demo.service;

import com.example.demo.entity.Alamat;
import java.util.List;

public interface AlamatService {
    List<Alamat> getAll();

    Alamat getById(Long id);

    Alamat create(Alamat alamat);

    Alamat update(Long id, Alamat alamat);

    void delete(Long id);
}