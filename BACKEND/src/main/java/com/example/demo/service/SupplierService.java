package com.example.demo.service;

import com.example.demo.entity.Supplier;
import java.util.List;

public interface SupplierService {
    List<Supplier> getAll();

    Supplier getById(Long id);

    Supplier create(Supplier supplier);

    Supplier update(Long id, Supplier supplier);

    void delete(Long id);
}