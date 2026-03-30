package com.example.demo.service.impl;

import com.example.demo.entity.Alamat;
import com.example.demo.repository.AlamatRepository;
import com.example.demo.service.AlamatService;

import org.springframework.stereotype.Service;

import java.util.List;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class AlamatServiceImpl implements AlamatService {
    private final AlamatRepository repo;

    public AlamatServiceImpl(AlamatRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Alamat> getAll() {
        return repo.findAll();
    }

    @Override
    public Alamat getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Alamat tidak ditemukan"));
    }

    @Override
    public Alamat create(Alamat alamat) {
        return repo.save(alamat);
    }

    @Override
    public Alamat update(Long id, Alamat alamat) {
        Alamat existing = getById(id);
        existing.setAlamat(alamat.getAlamat());
        existing.setUser(alamat.getUser());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}