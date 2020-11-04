package com.example.demo.service.impl;


import com.example.demo.dao.ChatDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.model.Chat;
import com.example.demo.model.Product;
import com.example.demo.service.ChatService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public void save(Product pro) {
        productDao.save(pro);
    }

    @Override
    public void update(Product pro) {
        productDao.update(pro);
    }


    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }

}