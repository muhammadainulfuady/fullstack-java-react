package com.example.demo.service.impl;

import com.example.demo.entity.Brand;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BrandRepository;
import com.example.demo.service.BrandService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository repo;

    public BrandServiceImpl(BrandRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Brand> getAll() {
        return repo.findAll();
    }

    @Override
    public Brand getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand tidak ditemukan"));
    }

    @Override
    public Brand create(Brand brand) {
        return repo.save(brand);
    }

    @Override
    public Brand update(Long id, Brand brand) {
        Brand existing = getById(id);
        existing.setNamaBrand(brand.getNamaBrand());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}