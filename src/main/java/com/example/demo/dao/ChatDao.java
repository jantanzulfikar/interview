package com.example.demo.dao;

import com.example.demo.model.Chat;
import com.example.demo.model.Customer;

import java.util.List;

public interface ChatDao {
    void save(Chat cus);
    void update(Chat cus);
    List<Chat> getChat(Long idSender , Long idReciver);
    void deleteChatByID(Long id);
    void updateSeen(Long idSender , Long idReciver);

}