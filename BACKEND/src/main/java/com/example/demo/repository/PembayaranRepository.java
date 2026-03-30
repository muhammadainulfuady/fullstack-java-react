package com.example.demo.repository;

import com.example.demo.entity.Pembayaran;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PembayaranRepository extends JpaRepository<Pembayaran, Long> {
}