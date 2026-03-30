package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Kategori;

public interface KategoriRepository extends JpaRepository<Kategori, Long> {
}