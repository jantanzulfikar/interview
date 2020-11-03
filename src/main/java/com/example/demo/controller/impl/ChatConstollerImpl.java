package com.example.demo.controller.impl;

import com.example.demo.controller.ChatController;
import com.example.demo.model.Chat;
import com.example.demo.service.ChatService;
import com.example.demo.util.JsonResponse;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.HashMap;

@RestController
public class ChatConstollerImpl implements ChatController {
    Logger logger = Logger.getLogger(ChatConstollerImpl.class.getName());

    @Autowired
    private JsonResponse jsonResponse;


    @Autowired
    private ChatService chatService;



    @Override
    public String chatPosting(@RequestBody String request, @RequestHeader HttpHeaders headers) {

        logger.info("incoming request Chat Posting : " + request);
        logger.info("incoming request Chat Posting : " + headers.get("ads"));

        Gson objGson = new Gson();
        Chat obj = objGson.fromJson(request , Chat.class);
        if (obj.getText() == null || obj.getText().isEmpty()) {
            jsonResponse.build("CT-01");
        }

        if (obj.getIdSender() == 0 ) {
            jsonResponse.build("CT-02");
        }

        if (obj.getIdReciver() == 0 ) {
            jsonResponse.build("CT-03");
        }

        if (obj.getType()== 0) {
            jsonResponse.build("CT-04");
        }

        if (obj.getStatus() == null) {
            jsonResponse.build("CT-05");
        }

        chatService.save(obj);
        return jsonResponse.build("RC-00");
    }



    @Override
    public String chatInquiry(@RequestBody String request, @RequestHeader HttpHeaders headers) {

        logger.info("incoming request chatInquiry : " + request);
        logger.info("incoming request chatInquiry : " + headers.get("ads"));

        Gson objGson = new Gson();
        Chat obj = objGson.fromJson(request , Chat.class);

        if (obj.getIdSender() == 0 ) {
            jsonResponse.build("CT-02");
        }

        if (obj.getIdReciver() == 0 ) {
            jsonResponse.build("CT-03");
        }

        List<Chat> chats = chatService.getChat(obj.getIdSender() , obj.getIdReciver());
        logger.info("count Data : " + chats.size());
        if (chats == null) {
            jsonResponse.build("CT-06");
        }

        JSONObject objData = new JSONObject();
        Map<String , Object> result = new HashMap<String , Object>();
        List<Map<String, Object>> resultAll = new ArrayList<Map<String,Object>>();
        for (Chat chat : chats) {
            result.put("id", chat.getId());
            result.put("senderId", chat.getIdSender());
            result.put("reciverId", chat.getIdReciver());
            result.put("text", chat.getText());
            result.put("type", chat.getType());
            result.put("status", chat.getStatus());
            result.put("created", chat.getCreated());
            resultAll.add( result);
        }
        return objGson.toJson(resultAll);
    }


    @Override
    public String chatDelete(@RequestBody String request, @RequestHeader HttpHeaders headers) {

        logger.info("incoming request Chat Delete : " + request);
        logger.info("incoming request Chat Delete : " + headers.get("ads"));

        Gson objGson = new Gson();
        Chat obj = objGson.fromJson(request , Chat.class);
        if (obj.getId() == 0) {
            jsonResponse.build("CT-07");
        }

        chatService.deleteById(obj.getId());

        return jsonResponse.build("RC-00");
    }


    @Override
    public String seenUpdate(@RequestBody String request, @RequestHeader HttpHeaders headers) {

        logger.info("incoming request Chat seenUpdate : " + request);
        logger.info("incoming request Chat seenUpdate : " + headers.get("ads"));

        Gson objGson = new Gson();
        Chat obj = objGson.fromJson(request , Chat.class);

        if (obj.getIdSender() == 0 ) {
            jsonResponse.build("CT-02");
        }

        if (obj.getIdReciver() == 0 ) {
            jsonResponse.build("CT-03");
        }

        chatService.updateSeen(obj.getIdSender() , obj.getIdReciver());

        return jsonResponse.build("RC-00");
    }








}