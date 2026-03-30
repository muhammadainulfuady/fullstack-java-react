package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "gambar_produk")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GambarProduk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gambar")
    private Long idGambar;

    @NotNull(message = "Produk wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_produk")
    private Produk produk;

    @NotBlank(message = "URL Gambar atau file gambar tidak boleh kosong")
    @Column(name = "url")
    private String url;
}