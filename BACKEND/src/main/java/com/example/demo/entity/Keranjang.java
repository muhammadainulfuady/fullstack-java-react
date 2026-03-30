package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "keranjang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keranjang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_keranjang")
    private Long idKeranjang;

    @NotNull(message = "Data relasi user wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}