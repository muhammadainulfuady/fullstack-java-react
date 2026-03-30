package com.example.demo.service;

import com.example.demo.entity.LogAktivitas;
import java.util.List;

public interface LogAktivitasService {
    List<LogAktivitas> getAll();

    LogAktivitas getById(Long id);

    LogAktivitas create(LogAktivitas logAktivitas);

    LogAktivitas update(Long id, LogAktivitas logAktivitas);

    void delete(Long id);
}