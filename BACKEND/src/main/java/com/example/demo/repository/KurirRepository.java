package com.example.demo.repository;

import com.example.demo.entity.Kurir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KurirRepository extends JpaRepository<Kurir, Long> {
}