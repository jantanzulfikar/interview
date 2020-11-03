package com.example.demo.service.impl;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.MemberDao;
import com.example.demo.model.Customer;
import com.example.demo.model.Member;
import com.example.demo.service.CustomerService;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDao memberDao;

    @Override
    public void save(Member mem) {
        memberDao.save(mem);
    }

    @Override
    public void update(Member mem) {
        memberDao.update(mem);
    }

    @Override
    public void getCustomerById(long cust_id) {
        Member cust = memberDao.findCustomerById(cust_id);
        System.out.println(cust);
    }

    @Override
    public void getCustomerNameById(long cust_id) {
        String name = memberDao.findNameById(cust_id);
        System.out.println("Customer's name = " + name);
    }

    @Override
    public void getTotalNumerCustomer() {
        int totalNumberCustomer = memberDao.getTotalNumberCustomer();
        System.out.println("Total Number Customer is: " + totalNumberCustomer);
    }

    @Override
    public int getCountCustomerById(Long id) {
        return memberDao.getCustomerById(id);
    }

    @Override
    public Member getLogin(String username ){
        return memberDao.getLogin(username);
    }

    @Override
    public Member getCustomerByPhone(String phone){
        return memberDao.getCustomerByPhone(phone);
    }


    @Override
    public int checkCountEmail(String emial) {
        return memberDao.getEmail(emial);
    }

    @Override
    public Member getLoginByEmail(String email ){
        return memberDao.getLoginByEmail(email);
    }
}