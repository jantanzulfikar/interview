package com.example.demo.dao.impl;

import com.example.demo.dao.PostingDao;
import com.example.demo.model.Chat;
import com.example.demo.model.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PostingDaoImpl extends JdbcDaoSupport implements PostingDao{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }


    @Override
    public void save(Posting pos) {
        pos.setCreated(new java.util.Date());
        pos.setUpdated(new java.util.Date());
        String sql = "INSERT INTO jz.t_posting " + "(m_customer_id, title , detail  ,type , qty , ammount , created , createdby , updated , status , image , count_seen , category) VALUES ( ? , ? ,? ,? ,? ,? ,?,?,?,?,?,?,? )";
        getJdbcTemplate().update(sql, new Object[]{
                pos.getCustomerId() , pos.getTitle()  , pos.getDetail() , pos.getType() , pos.getQty() , pos.getAmount() , pos.getCreated() , pos.getCreatedby() , pos.getUpdated() , pos.getStatus() , pos.getImage() , pos.getImage() , pos.getCountSenn(), pos.getCategory()
        });
    }


    @Override
    public void update(Posting pos) {

        //penjagaan agar tidak terupdate semua
        if (pos.getId() == 0) {
            return;
        }

        pos.setUpdated(new java.util.Date());
        String sql = "UPDATE  jz.t_posting SET " + " m_customer_id = ? , title = ? , detail = ? ,type = ? , qty = ? , ammount = ? , created = ? , createdby = ? , updated = ? , status = ? , image = ? , count_seen = ? , category = ?  where id = ? ";
        getJdbcTemplate().update(sql, new Object[]{
                pos.getCustomerId() , pos.getTitle()  , pos.getDetail() , pos.getType() , pos.getQty() , pos.getAmount() , pos.getCreated() , pos.getCreatedby() , pos.getUpdated() , pos.getStatus() , pos.getImage() , pos.getImage() , pos.getCountSenn(),pos.getCategory()
        });
    }


    public List<Posting> getLoginPosting(){
        try {
        String sql = "SELECT * FROM jz.t_posting ORDER BY created DESC limit 10";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Posting> result = new ArrayList<Posting>();
        for(Map<String, Object> row:rows){
            Posting posting = new Posting();
            posting.setId((Long)row.get("id"));
            posting.setCustomerId((Long)row.get("m_customer_id"));
            posting.setTitle((String)row.get("title"));
            posting.setDetail((String)row.get("detail"));
            posting.setType((String) row.get("type"));
            posting.setQty((Integer) row.get("qty"));
            posting.setAmount((BigDecimal) row.get("ammount"));
            posting.setCreated((Timestamp)row.get("created"));
            posting.setUpdated((Timestamp)row.get("updated"));
            posting.setUpdated((Timestamp) row.get("updated"));
            posting.setStatus((Boolean) row.get("status") );
            posting.setImage((String) row.get("image"));
            posting.setCountSenn((Integer) row.get("count_seen") );
            posting.setCategory((String) row.get("category"));
            result.add(posting);
        }

        return result;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public void deletePosintBytId (Long id ) {
        String sql = "DELETE FROM jz.t_posting WHERE id=?";
        getJdbcTemplate().update(sql, new Object[] {id});

    }


    public List<Posting> getByTitle(String title){
        try {
            String sql = "SELECT * FROM jz.t_posting WHERE title LIKE  '%" + title  + "%'  ORDER BY created DESC limit 100";
            List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
            List<Posting> result = new ArrayList<Posting>();
            for(Map<String, Object> row:rows){
                Posting posting = new Posting();
                posting.setId((Long)row.get("id"));
                posting.setCustomerId((Long)row.get("m_customer_id"));
                posting.setTitle((String)row.get("title"));
                posting.setDetail((String)row.get("detail"));
                posting.setType((String) row.get("type"));
                posting.setQty((Integer) row.get("qty"));
                posting.setAmount((BigDecimal) row.get("ammount"));
                posting.setCreated((Timestamp)row.get("created"));
                posting.setUpdated((Timestamp)row.get("updated"));
                posting.setUpdated((Timestamp) row.get("updated"));
                posting.setStatus((Boolean) row.get("status") );
                posting.setImage((String) row.get("image"));
                posting.setCountSenn((Integer) row.get("count_seen") );
                posting.setCategory((String) row.get("category"));
                result.add(posting);
            }

            return result;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }



    public void updateseen (Long id ) {
        String sql = "UPDATE jz.t_posting SET count_seen+1 WHERE id=?";
        getJdbcTemplate().update(sql, new Object[] {id});

    }

}