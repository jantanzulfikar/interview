package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Member;

import java.util.List;

public interface MemberService {

    void save(Member cus);
    void update(Member cus);
    void getCustomerById(long cust_id);
    void getCustomerNameById(long cust_id);
    void getTotalNumerCustomer();
    int getCountCustomerById(Long id);
    Member getLogin(String phone);
    Member getCustomerByPhone(String phone);
    int checkCountEmail(String email);

}

