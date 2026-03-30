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
@Table(name = "diskon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diskon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diskon")
    private Long idDiskon;

    @NotNull(message = "Produk wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_produk")
    private Produk produk;

    @NotNull(message = "Persen diskon wajib diisi")
    @Min(value = 0, message = "Diskon minimal 0%")
    @Max(value = 100, message = "Diskon maksimal 100%")
    @Column(name = "persen")
    private Integer persen;
}