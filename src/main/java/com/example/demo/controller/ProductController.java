package com.example.demo.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
public interface ProductController {

    @RequestMapping(path = "/all" , method = RequestMethod.POST)
    public String allProduct(@RequestBody String request, @RequestHeader HttpHeaders headers);








}