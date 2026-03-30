package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CartRequest;
import com.example.demo.entity.Keranjang;
import com.example.demo.entity.KeranjangItem;
import com.example.demo.entity.Produk;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.KeranjangItemRepository;
import com.example.demo.repository.KeranjangRepository;
import com.example.demo.repository.ProdukRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.KeranjangService;

import jakarta.transaction.Transactional;

@Service
public class KeranjangServiceImpl implements KeranjangService {

    private final KeranjangRepository repo; // Repository utama bawaanmu
    private final KeranjangItemRepository keranjangItemRepo;
    private final UserRepository userRepo;
    private final ProdukRepository produkRepo;

    // Inject semua repository yang dibutuhkan
    public KeranjangServiceImpl(KeranjangRepository repo, KeranjangItemRepository keranjangItemRepo,
            UserRepository userRepo, ProdukRepository produkRepo) {
        this.repo = repo;
        this.keranjangItemRepo = keranjangItemRepo;
        this.userRepo = userRepo;
        this.produkRepo = produkRepo;
    }

    // ==========================================
    // BASIC CRUD IMPLEMENTATION
    // ==========================================
    @Override
    public List<Keranjang> getAll() {
        return repo.findAll();
    }

    @Override
    public Keranjang getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Keranjang tidak ditemukan"));
    }

    @Override
    public Keranjang create(Keranjang keranjang) {
        return repo.save(keranjang);
    }

    @Override
    public Keranjang update(Long id, Keranjang keranjang) {
        Keranjang existing = getById(id);
        existing.setUser(keranjang.getUser());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ==========================================
    // BUSINESS LOGIC IMPLEMENTATION
    // ==========================================
    @Override
    @Transactional
    public KeranjangItem addToCart(String emailUser, CartRequest request) {
        User user = userRepo.findByEmail(emailUser)
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan"));

        Produk produk = produkRepo.findById(request.getIdProduk())
                .orElseThrow(() -> new ResourceNotFoundException("Produk tidak ditemukan"));

        // Cari keranjang user, kalau belum ada buatkan otomatis
        Keranjang keranjang = repo.findByUser(user)
                .orElseGet(() -> {
                    Keranjang keranjangBaru = new Keranjang();
                    keranjangBaru.setUser(user);
                    return repo.save(keranjangBaru);
                });

        Optional<KeranjangItem> existingItem = keranjangItemRepo.findByKeranjangAndProduk(keranjang, produk);

        if (existingItem.isPresent()) {
            KeranjangItem item = existingItem.get();
            item.setKuantitas(item.getKuantitas() + request.getKuantitas());
            return keranjangItemRepo.save(item);
        } else {
            KeranjangItem newItem = new KeranjangItem();
            newItem.setKeranjang(keranjang);
            newItem.setProduk(produk);
            newItem.setKuantitas(request.getKuantitas());
            return keranjangItemRepo.save(newItem);
        }
    }

    @Override
    public List<KeranjangItem> getCartItems(String emailUser) {
        User user = userRepo.findByEmail(emailUser)
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan"));

        Keranjang keranjang = repo.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Keranjang Anda kosong"));

        return keranjangItemRepo.findByKeranjang(keranjang);
    }
}