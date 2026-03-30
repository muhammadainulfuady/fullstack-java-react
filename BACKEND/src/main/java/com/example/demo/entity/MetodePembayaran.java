package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "metode_pembayaran")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodePembayaran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metode")
    private Long idMetode;

    @NotBlank(message = "Nama tidak boleh kosong")
    @Column(name = "nama_metode")
    private String namaMetode;
}