package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Alamat;

public interface AlamatRepository extends JpaRepository<Alamat, Long> {
}