package com.example.demo.dao.impl;

import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Customer;
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
public class CustomerDaoImpl extends JdbcDaoSupport implements CustomerDao{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }


    @Override
    public void save(Customer cus) {
        cus.setCreated(new java.util.Date());
        cus.setUpdated(new java.util.Date());
        cus.setCreatedby(1);
        cus.setLastLogin(new java.util.Date());

        String sql = "INSERT INTO jz.m_customer " + "(customer_name, customer_phone , user_name  ,password , email ," +
                " address  , status , created , updated , createdby , lastlogin , device_id) VALUES (?,?, ?, ? , ? , ? , ? ,? ,? ,? ,? ,? )";
        getJdbcTemplate().update(sql, new Object[]{
                cus.getCustomerName() , cus.getCustomerPhone()  , cus.getUsername() , cus.getPassword()  , cus.getEmail(),
                cus.getAddress()  , cus.getStatus(),cus.getCreated(),cus.getUpdated(),cus.getCreatedby(),
                cus.getLastLogin() , cus.getDeviceId()
        });
    }


    @Override
    public void update(Customer cus) {

        //penjagaan agar tidak terupdate semua
        if (cus.getId() == 0) {
            return;
        }

        cus.setUpdated(new java.util.Date());
        String sql = "UPDATE  jz.m_customer SET " + " customer_name = ? , customer_phone = ? , user_name = ? ,password = ? , email = ? ," +
                " address = ?  , status = ?, created  = ?, updated = ?, createdby = ? , lastlogin =? , device_id = ? where id = ?";
        getJdbcTemplate().update(sql, new Object[]{
                cus.getCustomerName() , cus.getCustomerPhone()  , cus.getUsername() , cus.getPassword()  , cus.getEmail(),
                cus.getAddress()  , cus.getStatus(),cus.getCreated(),cus.getUpdated(),cus.getCreatedby(),
                cus.getLastLogin() , cus.getDeviceId() , cus.getId()
        });


    }




    @Override
    public void inserBatch(List<Customer> customers) {
        String sql = "INSERT INTO jz.m_customer " + "(customer_name, customer_phone , user_name  ,password , email ," +
                " address , device_id , status , created , updated , createdby , lastlogin) VALUES (?, ?, ? , ? , ? , ? ,? ,? ,? ,? ,? ,?)";
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Customer customer = customers.get(i);
                ps.setString(1, customer.getCustomerName());
                ps.setString(2, customer.getCustomerPhone());
                ps.setString(3 , customer.getUsername());
                ps.setString(4 , customer.getPassword());
                ps.setString(5 , customer.getEmail());
                ps.setString(6 , customer.getAddress());
                ps.setString(7 , customer.getDeviceId());
                ps.setString(8 , customer.getStatus());
                ps.setDate(9 , (Date) customer.getCreated());
                ps.setDate(10 ,(Date) customer.getUpdated());
                ps.setInt(11 , customer.getCreatedby());
                ps.setDate(12 , (Date) customer.getLastLogin());
            }
            public int getBatchSize() {
                return customers.size();
            }
        });

    }


    public List<Customer> loadAllCustomer(){
        String sql = "SELECT * FROM jz.m_customer";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Customer> result = new ArrayList<Customer>();
        for(Map<String, Object> row:rows){
            Customer cus = new Customer();
            cus.setId((Long)row.get("id"));
            cus.setCustomerName((String)row.get("customer_name"));
            cus.setCustomerPhone((String) row.get("customer_phone"));
            result.add(cus);
        }

        return result;
    }

    @Override
    public Customer findCustomerById(long cust_id) {
        String sql = "SELECT * FROM jz.m_customer WHERE id = ?";
        return (Customer)getJdbcTemplate().queryForObject(sql, new Object[]{cust_id}, new RowMapper<Customer>(){
            @Override
            public Customer mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Customer cust = new Customer();
                cust.setId(rs.getLong("id"));
                cust.setCustomerName(rs.getString("customer_name"));
                cust.setCustomerPhone(rs.getString("customer_phone"));
                return cust;
            }
        });
    }

    @Override
    public String findNameById(long cust_id) {
        try {
            String sql = "SELECT name FROM jz.m_customer WHERE id = ?";
            return getJdbcTemplate().queryForObject(sql, new Object[]{cust_id}, String.class);
            } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public int getTotalNumberCustomer() {
        try {
            String sql = "SELECT Count(*) FROM jz.m_customer";
            int total = getJdbcTemplate().queryForObject(sql, Integer.class);
            return total;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public int getCustomerById(Long id){
         try {
            String sql = "SELECT Count(*) FROM jz.m_customer where id = ?";
            int result = getJdbcTemplate().queryForObject(sql ,new Object[]{id}, Integer.class);
            return result;

        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public Customer getLogin(String username) {
         try {
            String sql = "SELECT * FROM jz.m_customer WHERE user_name = ? ";
            return (Customer)getJdbcTemplate().queryForObject(sql, new Object[]{username},  new RowMapper<Customer>(){
                @Override
                public Customer mapRow(ResultSet rs, int rwNumber) throws SQLException {
                    Customer cust = new Customer();
                    cust.setId(rs.getLong("id"));
                    cust.setCustomerName(rs.getString("customer_name"));
                    cust.setCustomerPhone(rs.getString("customer_phone"));
                    cust.setUsername(rs.getString("user_name"));
                    cust.setPassword(rs.getString("password"));
                    cust.setEmail(rs.getString("email"));
                    cust.setAddress(rs.getString("address"));
                    cust.setDeviceId(rs.getString("device_id"));
                    cust.setStatus(rs.getString("status"));
                    cust.setCreated(rs.getTimestamp("created"));
                    cust.setUpdated(rs.getTimestamp("updated"));
                    cust.setCreatedby(rs.getInt("createdby"));
                    cust.setLastLogin(rs.getTimestamp("lastlogin"));
                    return cust;
                }
            });

        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }



    @Override
    public Customer getCustomerByPhone(String phone) {
        try {
            String sql = "SELECT * FROM jz.m_customer WHERE customer_phone = ?";
            return (Customer)getJdbcTemplate().queryForObject(sql, new Object[]{phone},  new RowMapper<Customer>(){
                @Override
                public Customer mapRow(ResultSet rs, int rwNumber) throws SQLException {
                    Customer cust = new Customer();
                    cust.setId(rs.getLong("id"));
                    cust.setCustomerName(rs.getString("customer_name"));
                    cust.setCustomerPhone(rs.getString("customer_phone"));
                    cust.setUsername(rs.getString("user_name"));
                    cust.setPassword(rs.getString("password"));
                    cust.setEmail(rs.getString("email"));
                    cust.setAddress(rs.getString("address"));
                    cust.setDeviceId(rs.getString("device_id"));
                    cust.setStatus(rs.getString("status"));
                    cust.setCreated(rs.getTimestamp("created"));
                    cust.setUpdated(rs.getTimestamp("updated"));
                    cust.setCreatedby(rs.getInt("createdby"));
                    cust.setLastLogin(rs.getTimestamp("lastlogin"));
                    return cust;
                }
            });

        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }


    @Override
    public int getCustomerByUsername(String username){
        try {
            String sql = "SELECT Count(*) FROM jz.m_customer where user_name = ?";
            int result = getJdbcTemplate().queryForObject(sql ,new Object[]{username}, Integer.class);
            return result;

        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }


}