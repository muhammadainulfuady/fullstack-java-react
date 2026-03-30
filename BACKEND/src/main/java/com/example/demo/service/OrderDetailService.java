package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.OrderDetail;

public interface OrderDetailService {
    List<OrderDetail> getAll();

    OrderDetail getById(Long id);

    OrderDetail create(OrderDetail orderDetail);

    OrderDetail update(Long id, OrderDetail orderDetail);

    void delete(Long id);
}