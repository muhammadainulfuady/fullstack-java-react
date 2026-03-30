package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartRequest {

    @NotNull(message = "ID Produk tidak boleh kosong")
    private Long idProduk;

    @NotNull(message = "Kuantitas tidak boleh kosong")
    @Min(value = 1, message = "Minimal kuantitas adalah 1")
    private Integer kuantitas;
}