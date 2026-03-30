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
@Table(name = "kurir")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kurir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kurir")
    private Long idKurir;

    @NotBlank(message = "Nama tidak boleh kosong")
    @Column(name = "nama_kurir")
    private String namaKurir;
}