package com.example.demo.service.impl;

import com.example.demo.entity.Supplier;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.SupplierService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository repo;

    public SupplierServiceImpl(SupplierRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Supplier> getAll() {
        return repo.findAll();
    }

    @Override
    public Supplier getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier tidak ditemukan"));
    }

    @Override
    public Supplier create(Supplier supplier) {
        return repo.save(supplier);
    }

    @Override
    public Supplier update(Long id, Supplier supplier) {
        Supplier existing = getById(id);
        existing.setNamaSupplier(supplier.getNamaSupplier());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}