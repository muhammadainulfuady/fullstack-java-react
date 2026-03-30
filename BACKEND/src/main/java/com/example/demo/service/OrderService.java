package com.example.demo.service;

import com.example.demo.entity.Order;
import java.util.List;

public interface OrderService {
    List<Order> getAll();

    Order getById(Long id);

    Order create(Order order);

    Order update(Long id, Order order);

    void delete(Long id);

    Order checkout(String emailUser);

    List<Order> getMyOrders(String emailUser);
}