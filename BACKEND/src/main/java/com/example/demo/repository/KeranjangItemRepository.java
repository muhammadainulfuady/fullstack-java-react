package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Keranjang;
import com.example.demo.entity.KeranjangItem;
import com.example.demo.entity.Produk;

@Repository
public interface KeranjangItemRepository extends JpaRepository<KeranjangItem, Long> {
    // Melihat semua isi dari sebuah keranjang
    List<KeranjangItem> findByKeranjang(Keranjang keranjang);

    // Mengecek apakah suatu produk sudah ada di dalam keranjang (untuk update
    // kuantitas)
    Optional<KeranjangItem> findByKeranjangAndProduk(Keranjang keranjang, Produk produk);

    // Mengosongkan keranjang setelah berhasil Checkout
    void deleteByKeranjang(Keranjang keranjang);
}