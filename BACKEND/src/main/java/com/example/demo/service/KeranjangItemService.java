package com.example.demo.service;

import com.example.demo.entity.KeranjangItem;
import java.util.List;

public interface KeranjangItemService {
    List<KeranjangItem> getAll();

    KeranjangItem getById(Long id);

    KeranjangItem create(KeranjangItem keranjangItem);

    KeranjangItem update(Long id, KeranjangItem keranjangItem);

    void delete(Long id);
}