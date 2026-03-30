package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Keranjang;
import com.example.demo.entity.User;

@Repository
public interface KeranjangRepository extends JpaRepository<Keranjang, Long> {
    Optional<Keranjang> findByUser(User user);
}