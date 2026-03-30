package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Produk;
import com.example.demo.service.ProdukService;
import org.springframework.data.domain.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produk")
public class ProdukController {
    private final ProdukService service;

    public ProdukController(ProdukService service) {
        this.service = service;
    }

    @GetMapping()
    public ApiResponse<List<Produk>> getAll() {
        return new ApiResponse<>(200, "Sukses Mengambil data produk", service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Produk> getById(@PathVariable Long id) {
        return new ApiResponse<>(200, "Sukses", service.getById(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<Produk>> search(@RequestParam("keyword") String keyword) {
        List<Produk> data = service.searchProduk(keyword);

        if (data.isEmpty()) {
            return new ApiResponse<>(404, "Produk dengan kata kunci '" + keyword + "' tidak ditemukan", null);
        }

        return new ApiResponse<>(200, "Berhasil menemukan produk", data);
    }

    @GetMapping("/page")
    public ApiResponse<Page<Produk>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page, // Default halaman pertama (dimulai dari 0)
            @RequestParam(defaultValue = "10") int size, // Default 10 data per halaman
            @RequestParam(defaultValue = "idProduk") String sortBy, // Default urut berdasarkan ID
            @RequestParam(defaultValue = "asc") String sortDir // Default urutan naik
    ) {
        Page<Produk> data = service.getAllPaginated(page, size, sortBy, sortDir);
        return new ApiResponse<>(200, "Berhasil mengambil data produk berhalaman", data);
    }

    @PostMapping
    public ApiResponse<Produk> create(@Valid @RequestBody Produk produk) {
        return new ApiResponse<>(201, "Sukses tambah data", service.create(produk));
    }

    @PutMapping("/{id}")
    public ApiResponse<Produk> update(@PathVariable Long id, @Valid @RequestBody Produk produk) {
        return new ApiResponse<>(200, "Sukses update data", service.update(id, produk));
    }

    @PatchMapping("/{id}")
    public ApiResponse<Produk> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Produk produk = service.getById(id);
        if (fields.containsKey("namaProduk"))
            produk.setNamaProduk((String) fields.get("namaProduk"));
        if (fields.containsKey("harga"))
            produk.setHarga(Double.valueOf(fields.get("harga").toString()));
        return new ApiResponse<>(200, "Sukses patch data", service.create(produk));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, "Sukses hapus data", null);
    }
}