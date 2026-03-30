package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Stok;
public interface StokRepository extends JpaRepository<Stok, Long>{
    
}
