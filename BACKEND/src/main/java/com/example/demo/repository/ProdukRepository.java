package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Produk;

public interface ProdukRepository extends JpaRepository<Produk, Long> {
    List<Produk> findByNamaProdukContainingIgnoreCase(String keyword);
}