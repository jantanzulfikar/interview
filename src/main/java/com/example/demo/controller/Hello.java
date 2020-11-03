package com.example.demo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/hello")
public interface Hello {


    @RequestMapping(path = "/hay" , method = RequestMethod.POST )
    public String sapa();


    @RequestMapping(path = "/coba" , method = RequestMethod.POST )
    public String coba(@RequestBody String request , @RequestHeader HttpHeaders headers);

    @RequestMapping(path = "/register" , method = RequestMethod.POST )
    public String register(@RequestBody String request , @RequestHeader HttpHeaders headers);




}

