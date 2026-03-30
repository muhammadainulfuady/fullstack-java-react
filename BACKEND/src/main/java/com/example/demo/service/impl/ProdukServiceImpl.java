package com.example.demo.service.impl;

import com.example.demo.entity.Produk;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProdukRepository;
import com.example.demo.service.ProdukService;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.*;

@Service
public class ProdukServiceImpl implements ProdukService {
    private final ProdukRepository repo;

    public ProdukServiceImpl(ProdukRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Produk> getAll() {
        return repo.findAll();
    }

    @Override
    public Produk getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produk tidak ditemukan"));
    }

    @Override
    public Produk create(Produk produk) {
        return repo.save(produk);
    }

    @Override
    public Produk update(Long id, Produk produk) {
        Produk existing = getById(id);
        existing.setNamaProduk(produk.getNamaProduk());
        existing.setHarga(produk.getHarga());
        existing.setBrand(produk.getBrand());
        existing.setKategori(produk.getKategori());
        existing.setSupplier(produk.getSupplier());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Produk> searchProduk(String keyword) {
        return repo.findByNamaProdukContainingIgnoreCase(keyword);
    }

    @Override
    public Page<Produk> getAllPaginated(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return repo.findAll(pageable);
    }
}