package com.example.demo.service.impl;

import com.example.demo.entity.Kota;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.KotaRepository;
import com.example.demo.service.KotaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KotaServiceImpl implements KotaService {
    private final KotaRepository repo;

    public KotaServiceImpl(KotaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Kota> getAll() {
        return repo.findAll();
    }

    @Override
    public Kota getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kota tidak ditemukan"));
    }

    @Override
    public Kota create(Kota kota) {
        return repo.save(kota);
    }

    @Override
    public Kota update(Long id, Kota kota) {
        Kota existing = getById(id);
        existing.setNamaKota(kota.getNamaKota());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}