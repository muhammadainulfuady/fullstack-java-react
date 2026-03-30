package com.example.demo.service;

import com.example.demo.entity.Diskon;
import java.util.List;

public interface DiskonService {
    List<Diskon> getAll();

    Diskon getById(Long id);

    Diskon create(Diskon diskon);

    Diskon update(Long id, Diskon diskon);

    void delete(Long id);
}