package com.example.demo.controller.impl;

import com.example.demo.controller.Hello;
import com.example.demo.service.CustomerService;
import com.example.demo.util.JsonResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@RestController
public class HelloImpl implements Hello {

    Logger logger = Logger.getLogger(HelloImpl.class.getName());

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JsonResponse jsonResponse;

    @Override
    public String sapa() {
        try {
            Map<String, Object> obj = new HashMap<String, Object>();
            obj.put("id", 1);
            obj.put("nama", "hafiz");
            Gson result = new Gson();
            return result.toJson(obj);
        }catch (Exception e){
            return "Error";
        }
    }

    public String coba(String request , HttpHeaders header) {
        try {
            logger.info("incoming request : " + request);
            logger.info("incoming request : " + header.get("ads"));
            JsonObject convertedObject = new Gson().fromJson(request, JsonObject.class);
            String nama = convertedObject.get("nama").getAsString();
            Gson gson = new Gson();
            Map<String , Object> resultObj = new HashMap<String , Object>();
            resultObj.put("Body" , nama );
            resultObj.put("Header" , header.get("ads").toString());
            return gson.toJson(resultObj);
        }catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    public String register(String request , HttpHeaders header) {
//        try {
//            logger.info("incoming request register : " + request);
//            logger.info("incoming request register : " + header.get("ads"));
//            JsonObject convertedObject = new Gson().fromJson(request, JsonObject.class);
//            String nama = convertedObject.get("nama").getAsString();
//            Long id = convertedObject.get("id").getAsLong();
//            int age = convertedObject.get("age").getAsInt();
//
//            if (id == null || nama == null || age == 0) {
//                return jsonResponse.build("RC-600");
//            }
//
//            int check = customerService.getCountCustomerById(id);
//            logger.info("Count Data : " + check);
//            if (check > 0) {
//                return jsonResponse.build("RC-01");
//            }
//
//            Customer c = new Customer();
//            c.setCustId(id);
//            c.setName(nama);
//            c.setAge(age);
//            customerService.insert(c);
//
//
//
//            Gson gson = new Gson();
//            Map<String , Object> resultObj = new HashMap<String , Object>();
//            resultObj.put("status" , "00" );
//            resultObj.put("statusDesc" , "Success");
//            return gson.toJson(resultObj);
//        }catch (Exception e) {
//            e.printStackTrace();
//            return "error";
//        }
        return null;
    }


}


