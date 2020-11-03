package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable{
    private static final long serialVersionUID = 1L;
    long id;
    String customerName;
    String customerPhone;
    String username;
    String password;
    String email;
    String address;
    String deviceId;
    String status;
    Date created;
    Date updated;
    Integer createdby;
    Date lastLogin;


    public Customer(){
    }

    public Customer(long id, String customerName, String customerPhone , String username , String password , String email , String address , String deviceId , String status , Date created , Date updated , Integer createdby , Date lastLogin)     {
        this.id = id;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.deviceId = deviceId;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.createdby = createdby;
        this.lastLogin = lastLogin;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "Customer [custId=" + id + ", name=" + customerName
                + "]";
    }

}