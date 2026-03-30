package com.example.demo.service.impl;

import com.example.demo.entity.LogAktivitas;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LogAktivitasRepository;
import com.example.demo.service.LogAktivitasService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LogAktivitasServiceImpl implements LogAktivitasService {
    private final LogAktivitasRepository repo;

    public LogAktivitasServiceImpl(LogAktivitasRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<LogAktivitas> getAll() {
        return repo.findAll();
    }

    @Override
    public LogAktivitas getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Log tidak ditemukan"));
    }

    @Override
    public LogAktivitas create(LogAktivitas logAktivitas) {
        return repo.save(logAktivitas);
    }

    @Override
    public LogAktivitas update(Long id, LogAktivitas logAktivitas) {
        LogAktivitas existing = getById(id);
        existing.setAdmin(logAktivitas.getAdmin());
        existing.setAktivitas(logAktivitas.getAktivitas());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}