package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.OrderService;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepo;
    private final KeranjangRepository keranjangRepo;
    private final KeranjangItemRepository keranjangItemRepo;
    private final OrderRepository orderRepo;
    private final OrderDetailRepository orderDetailRepo;
    private final ProdukRepository produkRepo;

    public OrderServiceImpl(UserRepository userRepo, KeranjangRepository keranjangRepo,
            KeranjangItemRepository keranjangItemRepo, OrderRepository orderRepo,
            OrderDetailRepository orderDetailRepo, ProdukRepository produkRepo) {
        this.userRepo = userRepo;
        this.keranjangRepo = keranjangRepo;
        this.keranjangItemRepo = keranjangItemRepo;
        this.orderRepo = orderRepo;
        this.orderDetailRepo = orderDetailRepo;
        this.produkRepo = produkRepo;
    }

    // ==========================================
    // BASIC CRUD (ADMIN)
    // ==========================================
    @Override
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order tidak ditemukan"));
    }

    @Override
    public Order create(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public Order update(Long id, Order order) {
        Order existing = getById(id);
        existing.setUser(order.getUser());
        existing.setTotal(order.getTotal());
        return orderRepo.save(existing);
    }

    @Override
    public void delete(Long id) {
        orderRepo.deleteById(id);
    }

    // ==========================================
    // BUSINESS LOGIC (USER CHECKOUT)
    // ==========================================
    @Override
    @Transactional
    public Order checkout(String emailUser) {
        User user = userRepo.findByEmail(emailUser)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        Keranjang keranjang = keranjangRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Keranjang tidak ditemukan"));

        List<KeranjangItem> cartItems = keranjangItemRepo.findByKeranjang(keranjang);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Keranjang Anda kosong, tidak bisa checkout!");
        }

        double totalBelanja = 0.0;
        for (KeranjangItem item : cartItems) {
            Produk p = item.getProduk();
            if (p.getStok() == null || p.getStok() < item.getKuantitas()) {
                throw new RuntimeException("Stok " + p.getNamaProduk() + " tidak mencukupi.");
            }
            totalBelanja += (p.getHarga() * item.getKuantitas());
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotal(totalBelanja);
        order.setTanggalOrder(LocalDateTime.now());
        order = orderRepo.save(order);

        for (KeranjangItem item : cartItems) {
            Produk p = item.getProduk();

            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduk(p);
            detail.setQty(item.getKuantitas());
            detail.setHargaSaatBeli(p.getHarga());
            orderDetailRepo.save(detail);

            p.setStok(p.getStok() - item.getKuantitas());
            produkRepo.save(p);
        }

        keranjangItemRepo.deleteByKeranjang(keranjang);
        return order;
    }

    @Override
    public List<Order> getMyOrders(String emailUser) {
        User user = userRepo.findByEmail(emailUser)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
        return orderRepo.findByUser(user);
    }
}