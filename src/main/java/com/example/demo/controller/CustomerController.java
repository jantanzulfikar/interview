package com.example.demo.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public interface CustomerController {


    @RequestMapping(path = "/registration" , method = RequestMethod.POST)
    public String registration(@RequestBody String request , @RequestHeader HttpHeaders headers);

    @RequestMapping(path = "/login" , method = RequestMethod.POST)
    public String login(@RequestBody String request , @RequestHeader HttpHeaders headers);

    @RequestMapping(path = "/inquiryChangePassword" , method = RequestMethod.POST)
    public String inquiryChangePassword(@RequestBody String request , @RequestHeader HttpHeaders headers);

    @RequestMapping(path = "/postingChangePassword" , method = RequestMethod.POST)
    public String postingChangePassword(@RequestBody String request , @RequestHeader HttpHeaders headers);

    @RequestMapping(path = "/updateProfile" , method = RequestMethod.POST)
    public String updateProfile(@RequestBody String request , @RequestHeader HttpHeaders headers);








}