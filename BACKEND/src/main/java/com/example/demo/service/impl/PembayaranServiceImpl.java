package com.example.demo.service.impl;

import com.example.demo.entity.Pembayaran;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PembayaranRepository;
import com.example.demo.service.PembayaranService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PembayaranServiceImpl implements PembayaranService {
    private final PembayaranRepository repo;

    public PembayaranServiceImpl(PembayaranRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Pembayaran> getAll() {
        return repo.findAll();
    }

    @Override
    public Pembayaran getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pembayaran tidak ditemukan"));
    }

    @Override
    public Pembayaran create(Pembayaran pembayaran) {
        return repo.save(pembayaran);
    }

    @Override
    public Pembayaran update(Long id, Pembayaran pembayaran) {
        Pembayaran existing = getById(id);
        existing.setOrder(pembayaran.getOrder());
        existing.setMetodePembayaran(pembayaran.getMetodePembayaran());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}