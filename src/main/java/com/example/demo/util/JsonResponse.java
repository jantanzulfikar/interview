package com.example.demo.util;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.HashMap;

@Service
public class JsonResponse {


    public String build(String code) {

        ResourceBundle bundle;

        try {
            ResourceBundle.clearCache();
            bundle = ResourceBundle.getBundle("response", new Locale("ind"));
        } catch (MissingResourceException e) {
            e.printStackTrace();
            return "";
        }

        String message = bundle.getString(code);
        code = code.substring(code.length()-2, code.length());
        Map<String , Object> obj = new HashMap<String , Object>();
        obj.put("status" , code);
        obj.put("satusDesc" , message);
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

}