package com.example.demo.dao;

import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerDao {
    void save(Customer cus);
    void update (Customer cus);
    void inserBatch(List<Customer> customers);
    List<Customer> loadAllCustomer();
    Customer findCustomerById(long cust_id);
    String findNameById(long cust_id);
    int getTotalNumberCustomer();
    int getCustomerById(Long customer);
    Customer getLogin(String username );
    Customer getCustomerByPhone(String phone);
    int getCustomerByUsername(String  username);
}