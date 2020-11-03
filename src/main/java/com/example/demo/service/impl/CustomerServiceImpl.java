package com.example.demo.service.impl;

import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public void save(Customer cus) {
        customerDao.save(cus);
    }

    @Override
    public void update(Customer cus) {
        customerDao.update(cus);
    }

    @Override
    public void insertBatch(List<Customer> customers) {
        customerDao.inserBatch(customers);
    }

    public void loadAllCustomer(){
        List<Customer> listCust = customerDao.loadAllCustomer();
        for(Customer cus: listCust){
            System.out.println(cus.toString());
        }
    }

    @Override
    public void getCustomerById(long cust_id) {
        Customer cust = customerDao.findCustomerById(cust_id);
        System.out.println(cust);
    }

    @Override
    public void getCustomerNameById(long cust_id) {
        String name = customerDao.findNameById(cust_id);
        System.out.println("Customer's name = " + name);
    }

    @Override
    public void getTotalNumerCustomer() {
        int totalNumberCustomer = customerDao.getTotalNumberCustomer();
        System.out.println("Total Number Customer is: " + totalNumberCustomer);
    }

    @Override
    public int getCountCustomerById(Long id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public Customer getLogin(String username ){
        return customerDao.getLogin(username);
    }

    @Override
    public Customer getCustomerByPhone(String phone){
        return customerDao.getCustomerByPhone(phone);
    }

    @Override
    public int getCountCustomerByUserName(String username) {
        return customerDao.getCustomerByUsername(username);
    }
}