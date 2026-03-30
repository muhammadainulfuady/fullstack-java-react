package com.example.demo.service;

import com.example.demo.entity.Kurir;
import java.util.List;

public interface KurirService {
    List<Kurir> getAll();

    Kurir getById(Long id);

    Kurir create(Kurir kurir);

    Kurir update(Long id, Kurir kurir);

    void delete(Long id);
}