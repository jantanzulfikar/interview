package com.example.demo.dao.impl;

import com.example.demo.dao.MemberDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.model.Member;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoImpl extends JdbcDaoSupport implements ProductDao{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }


    @Override
    public void save(Product pro) {

        String sql = "INSERT INTO m_product " + "(product_name , product_code , price , store_name , brand_name , category , desciption  " +
                ") VALUES (?,?, ?, ? , ? , ? , ? )";
        getJdbcTemplate().update(sql, new Object[]{
                pro.getProductName() , pro.getProductCode()  , pro.getPrice() , pro.getStoreName() , pro.getBrandName() , pro.getCategory() , pro.getDescription()
        });
    }


    @Override
    public void update(Product pro) {

        //penjagaan agar tidak terupdate semua
        if (pro.getId() == 0) {
            return;
        }

        String sql = "UPDATE  m_product SET " + "product_name = ? , product_code  = ?, price = ?, store_name = ?, brand_name = ?, category = ?, desciption = ? where id = ?";
        getJdbcTemplate().update(sql, new Object[]{
                pro.getProductName() , pro.getProductCode()  , pro.getPrice() , pro.getStoreName() , pro.getBrandName() , pro.getCategory() , pro.getDescription()
        });


    }



    public List<Product> getAllProduct(){
        try {
            String sql = "SELECT * FROM m_product order by id ";
            List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql , new Object[] {});
            List<Product> result = new ArrayList<Product>();
            for(Map<String, Object> row:rows){
                Product pro = new Product();
                pro.setId((Long)row.get("id"));
                pro.setProductCode((String )row.get("product_code"));
                pro.setPrice((BigDecimal) row.get("price"));
                pro.setStoreName((String) row.get("store_name"));
                pro.setBrandName((String) row.get("brand_name"));
                pro.setCategory((String) row.get("category"));
                pro.setDescription((String) row.get("desciption"));
                result.add(pro);
            }

            return result;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }




}