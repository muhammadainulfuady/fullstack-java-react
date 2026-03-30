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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime; // <-- TAMBAHAN 1: Import waktu

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;

    @NotNull(message = "User pembeli wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @NotNull(message = "Total belanja wajib diisi")
    @Min(value = 0, message = "Total tidak boleh negatif")
    @Column(name = "total")
    private Double total;

    @Column(name = "tanggal_order")
    private LocalDateTime tanggalOrder;
}