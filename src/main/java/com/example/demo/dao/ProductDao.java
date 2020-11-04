package com.example.demo.dao;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductDao {
    void save(Product mem);
    void update(Product mem);
    List<Product> getAllProduct();
}