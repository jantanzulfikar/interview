package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by janta on 03/11/2020.
 */
public class Member implements Serializable {


    String phone;
    long id;
    String firstName;
    String lastName;
    String gender;
    Date dob;
    String email;
    Date created;
    Date updated;
    Integer createdby;
    Date lastLogin;
    String password;
    String authentication;
    String deviceId;
//    public Member(long id, String phone, String firstName , String lastName , String gender , Date dob , String email )     {
//        this.id = id;
//        this.phone = phone;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.gender = gender;
//        this.email = email;
//        this.dob = dob;
//        this.created = created;
//        this.updated = updated;
//        this.createdby = createdby;
//        this.lastLogin = lastLogin;
//
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
