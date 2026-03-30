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
@Table(name = "pengiriman")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pengiriman")
    private Long idPengiriman;

    @NotNull(message = "Data relasi wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @NotNull(message = "Data relasi wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_kurir")
    private Kurir kurir;
}