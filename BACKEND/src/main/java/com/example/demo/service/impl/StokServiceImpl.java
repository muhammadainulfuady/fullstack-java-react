package com.example.demo.service.impl;

import com.example.demo.entity.Stok;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StokRepository;
import com.example.demo.service.StokService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StokServiceImpl implements StokService {
    private final StokRepository repo;

    public StokServiceImpl(StokRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Stok> getAll() {
        return repo.findAll();
    }

    @Override
    public Stok getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stok tidak ditemukan"));
    }

    @Override
    public Stok create(Stok stok) {
        return repo.save(stok);
    }

    @Override
    public Stok update(Long id, Stok stok) {
        Stok existing = getById(id);
        existing.setJumlah(stok.getJumlah());
        existing.setProduk(stok.getProduk());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}