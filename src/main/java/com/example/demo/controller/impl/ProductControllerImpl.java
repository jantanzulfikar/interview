package com.example.demo.controller.impl;

import com.example.demo.controller.MemberController;
import com.example.demo.controller.ProductController;
import com.example.demo.model.Member;
import com.example.demo.model.Product;
import com.example.demo.service.MemberService;
import com.example.demo.service.ProductService;
import com.example.demo.util.*;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@RestController
public class ProductControllerImpl implements ProductController {

    Logger logger = Logger.getLogger(ProductControllerImpl.class.getName());

    @Autowired
    private ProductService productService;

    @Autowired
    private JsonResponse jsonResponse;

    @Autowired
    private SendMail sendMail;

    @Autowired
    private WeakConcurrentHashMap weakConcurrentHashMap;

    @Autowired
    private SessionConcurrentHashMap sessionConcurrentHashMap;




    public String allProduct(String request , HttpHeaders header) {
        logger.info("incoming reuqest  All Product : " + request);
        logger.info("incoming reuqest  All Product  : " + header);
        String authHeader = header.get("Authentication").get(0).toString();
        Gson gson = new Gson();
        Member c = gson.fromJson( request , Member.class );
        String authCache  = sessionConcurrentHashMap.getSession(c.getPhone().substring(1 , 6) + c.getDeviceId().substring(1 , 6));
        logger.info("Check authCache : " + authCache);
        logger.info("AuthenticationHeader : " + authHeader);
        if (authHeader == null || authHeader.isEmpty()) {
            return jsonResponse.build("CP-04");
        }

        if (!authHeader.equals(authCache)) {
            return jsonResponse.build("CP-04");
        }

        List<Product> pro = productService.getAllProduct();
        logger.info("count Data : " + pro.size());
        if (pro == null) {
            jsonResponse.build("PR-01");
        }

        JSONObject objData = new JSONObject();
        Map<String , Object> result = new HashMap<String , Object>();
        List<Map<String, Object>> resultAll = new ArrayList<Map<String,Object>>();
        for (Product proD : pro) {
            result.put("id", proD.getId());
            result.put("productName" , proD.getProductName());
            result.put("productCode" , proD.getProductCode());
            result.put("price" , proD.getPrice());
            result.put("storeName" , proD.getStoreName());
            result.put("brandName" , proD.getBrandName());
            result.put("category" , proD.getCategory());
            result.put("description" , proD.getDescription());
            resultAll.add( result);
        }
        Gson objGson = new Gson();
        return objGson.toJson(resultAll);
    }



}