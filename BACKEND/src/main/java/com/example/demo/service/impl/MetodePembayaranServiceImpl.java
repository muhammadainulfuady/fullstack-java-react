package com.example.demo.service.impl;

import com.example.demo.entity.MetodePembayaran;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.MetodePembayaranRepository;
import com.example.demo.service.MetodePembayaranService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MetodePembayaranServiceImpl implements MetodePembayaranService {
    private final MetodePembayaranRepository repo;

    public MetodePembayaranServiceImpl(MetodePembayaranRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<MetodePembayaran> getAll() {
        return repo.findAll();
    }

    @Override
    public MetodePembayaran getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Metode tidak ditemukan"));
    }

    @Override
    public MetodePembayaran create(MetodePembayaran metodePembayaran) {
        return repo.save(metodePembayaran);
    }

    @Override
    public MetodePembayaran update(Long id, MetodePembayaran metodePembayaran) {
        MetodePembayaran existing = getById(id);
        existing.setNamaMetode(metodePembayaran.getNamaMetode());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}