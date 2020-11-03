package com.example.demo.service.impl;


import com.example.demo.dao.ChatDao;
import com.example.demo.dao.PostingDao;
import com.example.demo.model.Chat;
import com.example.demo.model.Posting;
import com.example.demo.service.ChatService;
import com.example.demo.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostingServiceImpl implements PostingService {

    @Autowired
    PostingDao postingDao;



    @Override
    public void save(Posting pos) {
        postingDao.save(pos);
    }

    @Override
    public void update(Posting pos) {
        postingDao.update(pos);
    }


    @Override
    public List<Posting> getLoginPosting() {
        return postingDao.getLoginPosting();
    }

    @Override
    public void deletePosintBytId(Long id) {
        postingDao.deletePosintBytId(id);
    }

    @Override
    public List<Posting> getByTitle(String title) {
        return postingDao.getByTitle(title);
    }

    @Override
    public void updateseen(Long id) {
        postingDao.updateseen(id);
    }

}