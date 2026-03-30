package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @NotNull(message = "Kota wajib dipilih")
    @ManyToOne
    @JoinColumn(name = "id_kota")
    private Kota kota;

    @NotBlank(message = "Alamat lengkap tidak boleh kosong")
    @Column(name = "nama")
    private String nama;

    @NotBlank(message = "Email lengkap tidak boleh kosong")
    @Email(message = "Format email tidak valid")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Password tidak boleh kosong")
    @Column(name = "password")
    private String password;
}