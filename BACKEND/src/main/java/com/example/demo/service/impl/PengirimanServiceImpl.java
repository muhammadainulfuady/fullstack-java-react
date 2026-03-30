package com.example.demo.service.impl;

import com.example.demo.entity.Pengiriman;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PengirimanRepository;
import com.example.demo.service.PengirimanService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PengirimanServiceImpl implements PengirimanService {
    private final PengirimanRepository repo;

    public PengirimanServiceImpl(PengirimanRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Pengiriman> getAll() {
        return repo.findAll();
    }

    @Override
    public Pengiriman getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pengiriman tidak ditemukan"));
    }

    @Override
    public Pengiriman create(Pengiriman pengiriman) {
        return repo.save(pengiriman);
    }

    @Override
    public Pengiriman update(Long id, Pengiriman pengiriman) {
        Pengiriman existing = getById(id);
        existing.setOrder(pengiriman.getOrder());
        existing.setKurir(pengiriman.getKurir());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}