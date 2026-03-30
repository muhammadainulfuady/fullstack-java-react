package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "kategori")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kategori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kategori")
    private Long idKategori;

    @NotBlank(message = "Nama tidak boleh kosong")
    @Column(name = "nama_kategori")
    private String namaKategori;
}