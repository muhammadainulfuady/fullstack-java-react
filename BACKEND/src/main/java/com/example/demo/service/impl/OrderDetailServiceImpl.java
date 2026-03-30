package com.example.demo.service.impl;

import com.example.demo.entity.OrderDetail;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.service.OrderDetailService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository repo;

    public OrderDetailServiceImpl(OrderDetailRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<OrderDetail> getAll() {
        return repo.findAll();
    }

    @Override
    public OrderDetail getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Detail tidak ditemukan"));
    }

    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        return repo.save(orderDetail);
    }

    @Override
    public OrderDetail update(Long id, OrderDetail orderDetail) {
        OrderDetail existing = getById(id);
        existing.setOrder(orderDetail.getOrder());
        existing.setProduk(orderDetail.getProduk());
        existing.setQty(orderDetail.getQty());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}