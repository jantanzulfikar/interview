package com.example.demo.dao;

import com.example.demo.model.Posting;

import java.util.List;

public interface PostingDao {
    void save(Posting pos);
    void update(Posting pos);
    List<Posting> getLoginPosting();
    void deletePosintBytId(Long id);
    List<Posting> getByTitle(String title);
    void updateseen(Long id);


}