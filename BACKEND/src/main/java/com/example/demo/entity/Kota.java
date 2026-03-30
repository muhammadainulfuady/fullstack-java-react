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
@Table(name = "kota")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kota")
    private Long idKota;

    @NotBlank(message = "Nama kota tidak boleh kosong")
    @Column(name = "nama_kota")
    private String namaKota;
}