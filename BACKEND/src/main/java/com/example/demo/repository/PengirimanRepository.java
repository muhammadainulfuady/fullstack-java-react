package com.example.demo.repository;

import com.example.demo.entity.Pengiriman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PengirimanRepository extends JpaRepository<Pengiriman, Long> {
}