package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {

    void save(Product pro);
    void update(Product pro);
    List<Product> getAllProduct();
}

