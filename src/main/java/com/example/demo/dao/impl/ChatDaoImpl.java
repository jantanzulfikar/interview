package com.example.demo.dao.impl;

import com.example.demo.dao.*;
import com.example.demo.model.Chat;
import com.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ChatDaoImpl extends JdbcDaoSupport implements ChatDao{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }


    @Override
    public void save(Chat cus) {
        cus.setCreated(new java.util.Date());
        cus.setUpdated(new java.util.Date());
        String sql = "INSERT INTO jz.t_chat " + "(id_sender, id_reciver , text  ,type , status , created , updated ) VALUES ( ? , ? ,? ,? ,? ,? ,? )";
        getJdbcTemplate().update(sql, new Object[]{
                cus.getIdSender() , cus.getIdReciver()  , cus.getText() , cus.getType()  , cus.getStatus(),
                cus.getCreated()  , cus.getUpdated()
        });
    }


    @Override
    public void update(Chat cus) {

        //penjagaan agar tidak terupdate semua
        if (cus.getId() == 0) {
            return;
        }

        cus.setUpdated(new java.util.Date());
        String sql = "UPDATE  jz.t_chat SET " + " id_sender = ? , id_reciver = ? , text = ? ,type = ? , status = ? ," +
                " created = ?  , updated = ? where id = ?";
        getJdbcTemplate().update(sql, new Object[]{
                cus.getIdSender() , cus.getIdReciver()  , cus.getText() , cus.getType()  , cus.getStatus(),
                cus.getCreated()  , cus.getUpdated()
        });
    }


    public List<Chat> getChat(Long idSender , Long idReciver){
        try {
        String sql = "SELECT * FROM jz.t_chat where id_sender = ? and id_reciver = ?";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql , new Object[] {idSender , idReciver});
        List<Chat> result = new ArrayList<Chat>();
        for(Map<String, Object> row:rows){
            Chat chat = new Chat();
            chat.setId((Long)row.get("id"));
            chat.setIdSender((Long)row.get("id_sender"));
            chat.setIdReciver((Long)row.get("id_reciver"));
            chat.setText((String)row.get("text"));
            chat.setType((Integer) row.get("type"));
            chat.setStatus((Boolean) row.get("status"));
            chat.setCreated((Timestamp)row.get("created"));
            chat.setUpdated((Timestamp)row.get("updated"));
            result.add(chat);
        }

        return result;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public void deleteChatByID (Long id ) {
        String sql = "delete from jz.t_chat where id=?";
        getJdbcTemplate().update(sql, new Object[] {id});

    }

    public void updateSeen(Long idSender , Long idReciver) {
        String sql = "UPDATE  jz.t_chat SET status = TRUE , updated = now()  where id_sender = ? and id_reciver = ? ";
        getJdbcTemplate().update(sql, new Object[]{
                idSender , idReciver
        });
    }
}