package com.example.demo.dao;

import com.example.demo.model.Customer;
import com.example.demo.model.Member;

import java.util.List;

public interface MemberDao {
    void save(Member mem);
    void update(Member mem);
    Member findCustomerById(long cust_id);
    String findNameById(long cust_id);
    int getTotalNumberCustomer();
    int getCustomerById(Long customer);
    Member getLogin(String phone);
    Member getCustomerByPhone(String phone);
    int getEmail(String email);
    Member getLoginByEmail(String email);
}