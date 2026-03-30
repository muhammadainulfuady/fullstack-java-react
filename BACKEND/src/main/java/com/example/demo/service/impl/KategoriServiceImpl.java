package com.example.demo.service.impl;

import com.example.demo.entity.Kategori;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.KategoriRepository;
import com.example.demo.service.KategoriService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KategoriServiceImpl implements KategoriService {
    private final KategoriRepository repo;

    public KategoriServiceImpl(KategoriRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Kategori> getAll() {
        return repo.findAll();
    }

    @Override
    public Kategori getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kategori tidak ditemukan"));
    }

    @Override
    public Kategori create(Kategori kategori) {
        return repo.save(kategori);
    }

    @Override
    public Kategori update(Long id, Kategori kategori) {
        Kategori existing = getById(id);
        existing.setNamaKategori(kategori.getNamaKategori());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}