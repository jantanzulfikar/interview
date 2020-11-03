package com.example.demo.service;

import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerService {

    void save(Customer cus);
    void update(Customer cus);
    void insertBatch(List<Customer> customers);
    void loadAllCustomer();
    void getCustomerById(long cust_id);
    void getCustomerNameById(long cust_id);
    void getTotalNumerCustomer();
    int getCountCustomerById(Long id);
    Customer getLogin(String username );
    Customer getCustomerByPhone(String phone);
    int getCountCustomerByUserName(String username);
}