package com.example.demo.service.impl;

import com.example.demo.entity.GambarProduk;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.GambarProdukRepository;
import com.example.demo.service.GambarProdukService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GambarProdukServiceImpl implements GambarProdukService {
    private final GambarProdukRepository repo;

    public GambarProdukServiceImpl(GambarProdukRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<GambarProduk> getAll() {
        return repo.findAll();
    }

    @Override
    public GambarProduk getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Gambar tidak ditemukan"));
    }

    @Override
    public GambarProduk create(GambarProduk gambarProduk) {
        return repo.save(gambarProduk);
    }

    @Override
    public GambarProduk update(Long id, GambarProduk gambarProduk) {
        GambarProduk existing = getById(id);
        existing.setUrl(gambarProduk.getUrl());
        existing.setProduk(gambarProduk.getProduk());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}