package com.example.demo.service.impl;

import com.example.demo.entity.Diskon;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiskonRepository;
import com.example.demo.service.DiskonService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiskonServiceImpl implements DiskonService {
    private final DiskonRepository repo;

    public DiskonServiceImpl(DiskonRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Diskon> getAll() {
        return repo.findAll();
    }

    @Override
    public Diskon getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Diskon tidak ditemukan"));
    }

    @Override
    public Diskon create(Diskon diskon) {
        return repo.save(diskon);
    }

    @Override
    public Diskon update(Long id, Diskon diskon) {
        Diskon existing = getById(id);
        existing.setPersen(diskon.getPersen());
        existing.setProduk(diskon.getProduk());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}