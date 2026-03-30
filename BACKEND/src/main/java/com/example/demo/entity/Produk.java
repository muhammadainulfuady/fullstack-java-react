package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produk")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produk")
    private Long idProduk;

    @NotNull(message = "Brand wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_brand")
    private Brand brand;

    @NotNull(message = "Supplier wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_supplier")
    private Supplier supplier;

    @NotNull(message = "Kategori wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_kategori")
    private Kategori kategori;

    @NotBlank(message = "Nama produk tidak boleh kosong")
    @Column(name = "nama_produk")
    private String namaProduk;

    @NotNull(message = "Harga wajib diisi")
    @Column(name = "harga")
    private Double harga;

    @Column(name = "tanggal_order")
    private LocalDateTime tanggalOrder;

    @Column(name = "stok")
    private Integer stok;
}