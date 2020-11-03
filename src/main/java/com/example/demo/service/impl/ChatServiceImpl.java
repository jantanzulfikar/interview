package com.example.demo.service.impl;


import com.example.demo.dao.ChatDao;
import com.example.demo.model.Chat;
import com.example.demo.service.ChatService;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatDao chatDao;

    @Override
    public void save(Chat chat) {
        chatDao.save(chat);
    }

    @Override
    public void update(Chat chat) {
        chatDao.update(chat);
    }


    @Override
    public List<Chat> getChat(Long idSender , Long idReciver) {
        return chatDao.getChat(idSender , idReciver);
    }

    @Override
    public void deleteById(Long id) {
        chatDao.deleteChatByID(id);
    }

    @Override
    public void updateSeen(Long idSender , Long idReciver) {
        chatDao.updateSeen(idSender , idReciver);
    }

}