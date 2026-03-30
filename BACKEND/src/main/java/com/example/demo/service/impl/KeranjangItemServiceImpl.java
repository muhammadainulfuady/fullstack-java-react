package com.example.demo.service.impl;

import com.example.demo.entity.KeranjangItem;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.KeranjangItemRepository;
import com.example.demo.service.KeranjangItemService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KeranjangItemServiceImpl implements KeranjangItemService {
    private final KeranjangItemRepository repo;

    public KeranjangItemServiceImpl(KeranjangItemRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<KeranjangItem> getAll() {
        return repo.findAll();
    }

    @Override
    public KeranjangItem getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item tidak ditemukan"));
    }

    @Override
    public KeranjangItem create(KeranjangItem keranjangItem) {
        return repo.save(keranjangItem);
    }

    @Override
    public KeranjangItem update(Long id, KeranjangItem keranjangItem) {
        KeranjangItem existing = getById(id);
        existing.setKeranjang(keranjangItem.getKeranjang());
        existing.setProduk(keranjangItem.getProduk());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}