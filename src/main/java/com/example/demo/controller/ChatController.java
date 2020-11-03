package com.example.demo.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/chat")
public interface ChatController {


    @RequestMapping(path = "/posting" , method = RequestMethod.POST)
    public String chatPosting(@RequestBody String request, @RequestHeader HttpHeaders headers);

    @RequestMapping(path = "/inquiry" , method = RequestMethod.POST)
    public String chatInquiry(@RequestBody String request, @RequestHeader HttpHeaders headers);


    @RequestMapping(path = "/delete" , method = RequestMethod.POST)
    public String chatDelete(@RequestBody String request, @RequestHeader HttpHeaders headers);

    @RequestMapping(path = "/seenUpdate" , method = RequestMethod.POST)
    public String seenUpdate(@RequestBody String request, @RequestHeader HttpHeaders headers);









}