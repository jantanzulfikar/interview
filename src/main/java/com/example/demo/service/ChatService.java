package com.example.demo.service;

import com.example.demo.model.Chat;
import com.example.demo.model.Customer;

import java.util.List;

public interface ChatService {

    void save(Chat cus);
    void update(Chat cus);
    List<Chat> getChat(Long idSender , Long idReciver);
    void deleteById (Long id);
    void updateSeen(Long idSender , Long idReciver);
}