package com.example.demo.service.impl;

import com.example.demo.entity.Kurir;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.KurirRepository;
import com.example.demo.service.KurirService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KurirServiceImpl implements KurirService {
    private final KurirRepository repo;

    public KurirServiceImpl(KurirRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Kurir> getAll() {
        return repo.findAll();
    }

    @Override
    public Kurir getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kurir tidak ditemukan"));
    }

    @Override
    public Kurir create(Kurir kurir) {
        return repo.save(kurir);
    }

    @Override
    public Kurir update(Long id, Kurir kurir) {
        Kurir existing = getById(id);
        existing.setNamaKurir(kurir.getNamaKurir());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}