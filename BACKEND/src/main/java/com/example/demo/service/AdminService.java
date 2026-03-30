package com.example.demo.service;

import com.example.demo.entity.Admin;
import java.util.List;

public interface AdminService {
    List<Admin> getAll();

    Admin getById(Long id);

    Admin create(Admin admin);

    Admin update(Long id, Admin admin);

    void delete(Long id);
}