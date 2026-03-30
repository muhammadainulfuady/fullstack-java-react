package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "keranjang_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeranjangItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long idItem;

    @NotNull(message = "Data relasi wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_keranjang")
    private Keranjang keranjang;

    @NotNull(message = "Data relasi wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_produk")
    private Produk produk;

    @NotNull(message = "Kuantitas tidak boleh kosong")
    @Min(value = 1, message = "Kuantitas minimal 1 barang")
    @Column(name = "kuantitas")
    private Integer kuantitas;
}