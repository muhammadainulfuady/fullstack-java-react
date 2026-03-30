package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.GambarProduk;

public interface GambarProdukRepository extends JpaRepository<GambarProduk, Long> {
}