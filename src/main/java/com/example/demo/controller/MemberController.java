package com.example.demo.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/member")
public interface MemberController {


    @RequestMapping(path = "/registration" , method = RequestMethod.POST)
    public String registration(@RequestBody String request, @RequestHeader HttpHeaders headers);

    @RequestMapping(path = "/login" , method = RequestMethod.POST)
    public String login(@RequestBody String request, @RequestHeader HttpHeaders headers);

    @RequestMapping(path = "/updateProfile" , method = RequestMethod.POST)
    public String updateProfile(@RequestBody String request, @RequestHeader HttpHeaders headers);


    @RequestMapping(path = "/profile" , method = RequestMethod.POST)
    public String profile(@RequestBody String request, @RequestHeader HttpHeaders headers);








}