package com.example.demo.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/posting")
public interface PostingController {


    @RequestMapping(path = "/posting" , method = RequestMethod.POST)
    public String posting(@RequestBody String request, @RequestHeader HttpHeaders headers);

    @RequestMapping(path = "/inquiryLogin" , method = RequestMethod.POST)
    public String inquiry(@RequestBody String request, @RequestHeader HttpHeaders headers);


    @RequestMapping(path = "/delete" , method = RequestMethod.POST)
    public String delete(@RequestBody String request, @RequestHeader HttpHeaders headers);

    @RequestMapping(path = "/seenUpdate" , method = RequestMethod.POST)
    public String seenUpdate(@RequestBody String request, @RequestHeader HttpHeaders headers);









}