package com.example.demo.dao.impl;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.MemberDao;
import com.example.demo.model.Customer;
import com.example.demo.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MemberDaoImpl extends JdbcDaoSupport implements MemberDao{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }


    @Override
    public void save(Member mem) {
        mem.setCreated(new java.util.Date());
        mem.setUpdated(new java.util.Date());
        mem.setCreatedby(1);
        mem.setLastLogin(new java.util.Date());

        String sql = "INSERT INTO m_member " + "(first_name, last_name , gender  ,bod , phone ,  email , created  , updated , createdby  , lastlogin   , password  , device_id   " +
                ") VALUES (?,?, ?, ? , ? , ? , ? ,? ,?,? , ? , ?)";
        getJdbcTemplate().update(sql, new Object[]{
                mem.getFirstName() , mem.getLastName() , mem.getGender() , mem.getDob() , mem.getPhone() , mem.getEmail() , mem.getCreated() , mem.getUpdated() , mem.getCreatedby(),
                mem.getLastLogin() , mem.getPassword() , mem.getDeviceId()

        });
    }


    @Override
    public void update(Member mem) {

        //penjagaan agar tidak terupdate semua
        if (mem.getId() == 0) {
            return;
        }

        mem.setUpdated(new java.util.Date());
        String sql = "UPDATE  m_member SET " + " first_name = ? , last_name = ? , gender = ? ,bod = ? , phone = ? ," +
                " email = ?  , created  = ?, updated = ?, createdby = ? , lastlogin =? , password = ? , device_id = ? where id = ?";
        getJdbcTemplate().update(sql, new Object[]{
                mem.getFirstName() , mem.getLastName() , mem.getGender() , mem.getDob() , mem.getPhone() , mem.getEmail() , mem.getCreated() , mem.getUpdated() , mem.getCreatedby(),
                mem.getLastLogin() , mem.getPassword() , mem.getId() , mem.getDeviceId()
        });


    }


    @Override
    public Member findCustomerById(long cust_id) {
        String sql = "SELECT * FROM m_member WHERE id = ?";
        return (Member) getJdbcTemplate().queryForObject(sql, new Object[]{cust_id}, new RowMapper<Member>(){
            @Override
            public Member mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Member mem = new Member();
                mem.setId(rs.getLong("id"));
                mem.setFirstName(rs.getString("first_name"));
                mem.setPhone(rs.getString("phone"));
                return mem;
            }
        });
    }

    @Override
    public String findNameById(long cust_id) {
        try {
            String sql = "SELECT first_name  FROM m_member WHERE id = ?";
            return getJdbcTemplate().queryForObject(sql, new Object[]{cust_id}, String.class);
            } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public int getTotalNumberCustomer() {
        try {
            String sql = "SELECT Count(*) FROM m_member";
            int total = getJdbcTemplate().queryForObject(sql, Integer.class);
            return total;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public int getCustomerById(Long id){
         try {
            String sql = "SELECT Count(*) FROM m_member where id = ?";
            int result = getJdbcTemplate().queryForObject(sql ,new Object[]{id}, Integer.class);
            return result;

        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public Member getLogin(String username) {
         try {
            String sql = "SELECT * FROM m_member WHERE phone = ? ";
            return (Member) getJdbcTemplate().queryForObject(sql, new Object[]{username},  new RowMapper<Member>(){
                @Override
                public Member mapRow(ResultSet rs, int rwNumber) throws SQLException {
                    Member mem = new Member();
                    mem.setId(rs.getLong("id"));
                    mem.setPassword(rs.getString("password"));
                    mem.setLastName(rs.getString("last_name"));
                    mem.setFirstName(rs.getString("first_name"));
                    mem.setPassword(rs.getString("password"));
                    mem.setEmail(rs.getString("email"));
                    mem.setCreated(rs.getTimestamp("created"));
                    mem.setUpdated(rs.getTimestamp("updated"));
                    mem.setCreatedby(rs.getInt("createdby"));
                    mem.setLastLogin(rs.getTimestamp("lastlogin"));
                    mem.setPhone(rs.getString("phone"));
                    mem.setDeviceId(rs.getString("device_id"));
                    return mem;
                }
            });

        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }



    @Override
    public Member getCustomerByPhone(String phone) {
        try {
            String sql = "SELECT * FROM m_member WHERE phone = ?";
            return (Member) getJdbcTemplate().queryForObject(sql, new Object[]{phone},  new RowMapper<Member>(){
                @Override
                public Member mapRow(ResultSet rs, int rwNumber) throws SQLException {
                    Member mem = new Member();
                    mem.setId(rs.getLong("id"));
                    mem.setPassword(rs.getString("password"));
                    mem.setLastName(rs.getString("last_name"));
                    mem.setFirstName(rs.getString("first_name"));
                    mem.setPassword(rs.getString("password"));
                    mem.setEmail(rs.getString("email"));
                    mem.setCreated(rs.getTimestamp("created"));
                    mem.setUpdated(rs.getTimestamp("updated"));
                    mem.setCreatedby(rs.getInt("createdby"));
                    mem.setLastLogin(rs.getTimestamp("lastlogin"));
                    mem.setPhone(rs.getString("phone"));
                    mem.setDeviceId(rs.getString("device_id"));
                    return mem;
                }
            });

        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }



    @Override
    public int getEmail(String emial){
        try {
            String sql = "SELECT Count(*) FROM m_member where email = ?";
            int result = getJdbcTemplate().queryForObject(sql ,new Object[]{emial}, Integer.class);
            return result;

        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }



}