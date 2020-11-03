package com.example.demo.controller.impl;

import com.example.demo.controller.ChatController;
import com.example.demo.controller.PostingController;
import com.example.demo.model.Chat;
import com.example.demo.model.Posting;
import com.example.demo.service.ChatService;
import com.example.demo.service.PostingService;
import com.example.demo.util.JsonResponse;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class PostingConstollerImpl implements PostingController {
    Logger logger = Logger.getLogger(PostingConstollerImpl.class.getName());

    @Autowired
    private JsonResponse jsonResponse;


    @Autowired
    private PostingService postingService;



    @Override
    public String posting(@RequestBody String request, @RequestHeader HttpHeaders headers) {

        logger.info("incoming request Chat Posting : " + request);
        logger.info("incoming request Chat Posting : " + headers.get("ads"));

        Gson objGson = new Gson();
        Posting obj = objGson.fromJson(request , Posting.class);
        postingService.save(obj);
        return jsonResponse.build("RC-00");
    }



    @Override
    public String inquiry(@RequestBody String request, @RequestHeader HttpHeaders headers) {

        logger.info("incoming request chatInquiry : " + request);
        logger.info("incoming request chatInquiry : " + headers.get("ads"));

        Gson objGson = new Gson();
        Chat obj = objGson.fromJson(request , Chat.class);

        List<Posting> postings = postingService.getLoginPosting();
        logger.info("count Data : " + postings.size());
        if (postings == null) {
            jsonResponse.build("CT-06");
        }

        return objGson.toJson(postings);
    }


    @Override
    public String delete(@RequestBody String request, @RequestHeader HttpHeaders headers) {

        logger.info("incoming request Chat Delete : " + request);
        logger.info("incoming request Chat Delete : " + headers.get("ads"));

        Gson objGson = new Gson();
        Chat obj = objGson.fromJson(request , Chat.class);
        if (obj.getId() == 0) {
            jsonResponse.build("PT-02");
        }
        postingService.deletePosintBytId(obj.getId());
        return jsonResponse.build("RC-00");
    }


    @Override
    public String seenUpdate(@RequestBody String request, @RequestHeader HttpHeaders headers) {

        logger.info("incoming request Chat seenUpdate : " + request);
        logger.info("incoming request Chat seenUpdate : " + headers.get("ads"));

        Gson objGson = new Gson();
        Chat obj = objGson.fromJson(request , Chat.class);
        if (obj.getId() == 0) {
            jsonResponse.build("PT-02");
        }
        postingService.updateseen(obj.getId());
        return jsonResponse.build("RC-00");
    }








}