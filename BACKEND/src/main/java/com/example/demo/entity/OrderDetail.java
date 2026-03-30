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
@Table(name = "order_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail")
    private Long idDetail;

    @NotNull(message = "Order wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @NotNull(message = "Produk wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_produk")
    private Produk produk;

    @NotNull(message = "Quantity wajib diisi")
    @Min(value = 1, message = "Minimal pembelian adalah 1")
    @Column(name = "qty")
    private Integer qty;

    @NotNull(message = "Harga beli wajib diisi")
    @Column(name = "harga_saat_beli")
    private Double hargaSaatBeli;
}