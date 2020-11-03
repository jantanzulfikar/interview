package com.example.demo.controller.impl;

import com.example.demo.controller.CustomerController;
import com.example.demo.controller.MemberController;
import com.example.demo.model.Customer;
import com.example.demo.model.Member;
import com.example.demo.service.CustomerService;
import com.example.demo.service.MemberService;
import com.example.demo.util.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
public class MemberControllerImpl implements MemberController {

    Logger logger = Logger.getLogger(MemberControllerImpl.class.getName());

    @Autowired
    private MemberService memberService;

    @Autowired
    private JsonResponse jsonResponse;

    @Autowired
    private SendMail sendMail;

    @Autowired
    private WeakConcurrentHashMap weakConcurrentHashMap;

    @Autowired
    private SessionConcurrentHashMap sessionConcurrentHashMap;

    @Override
    public String registration(String request , HttpHeaders header){
        try {
            logger.info("incoming request register : " + request);
            Gson gson = new Gson();
            Member c = gson.fromJson( request , Member.class );
            logger.info("name : " + c.getFirstName());
            logger.info("phone : " + c.getPhone());
            if ( c.getFirstName() == null || c.getPhone() == null) {
                return jsonResponse.build("RC-99");
            }

            Member checkCust = memberService.getCustomerByPhone(c.getPhone());
            logger.info("Count Data : " + checkCust);
            if (checkCust != null) {
                return jsonResponse.build("RC-01");
            }

            int checkEmail = memberService.checkCountEmail(c.getEmail());
            if (checkEmail > 0 ) {
                return jsonResponse.build("RC-02");
            }

            String hashPassword = generateMD5.getMd5(c.getPassword());
            c.setPassword(hashPassword);
            memberService.save(c);
            return jsonResponse.build("RC-00");

        }catch (Exception e) {
            e.printStackTrace();
            return jsonResponse.build("RC-99");
        }

    }

    @Override
    public String login(String request , HttpHeaders header) {
        logger.info("incoming reuqest login : " + request);
        logger.info("incoming reuqest header login : " + header);
        try {
            Gson gson = new Gson();
            Member c = gson.fromJson( request , Member.class );
            if (c.getPhone() == null || c.getPassword() == null ) {
                return jsonResponse.build("LG-02");
            }

            String hashPass = generateMD5.getMd5(c.getPassword());
            Member checkLogin = memberService.getLogin(c.getPhone());
            if (checkLogin == null) {
                if(c.getEmail() == null) {
                    return jsonResponse.build("LG-03");
                }

                checkLogin = memberService.getLoginByEmail(c.getEmail());
                if (checkLogin == null) {
                    return jsonResponse.build("LG-03");
                }
            }

            if (!hashPass.equals(checkLogin.getPassword())) {
                return jsonResponse.build("LG-03");
            }

            String sessionId = generateMD5.getMd5(c.getDeviceId() + c.getPhone());
            sessionConcurrentHashMap.put(c.getPhone() + c.getDeviceId() , sessionId);
            checkLogin.setSessionId(sessionId);
            return gson.toJson(checkLogin);

        }catch (Exception e){
            e.printStackTrace();
            return jsonResponse.build("RC-99");
        }
    }


    

    public String updateProfile(String request , HttpHeaders header) {
        logger.info("incoming reuqest update Profile : " + request);
        logger.info("incoming reuqest update Profile  : " + header);
        logger.info("incoming request register : " + header.get("sessionId"));
        String sessionFromHeader = header.get("sessionId").get(0).toString();
        logger.info("sessionFromHeader : " + sessionFromHeader);

        try {
            Gson gson = new Gson();
            Member c = gson.fromJson( request , Member.class );
            String sessionFromcche  = sessionConcurrentHashMap.getSession(c.getPhone()+ c.getDeviceId().toString());
            logger.info("Check session : " + sessionFromcche);

            Member check = memberService.getCustomerByPhone(c.getPhone());
            if (check == null) {
                return jsonResponse.build("CP-01");
            }

            if (sessionFromHeader == null || sessionFromHeader.isEmpty()) {
                return jsonResponse.build("CP-05");
            }

            if (!sessionFromHeader.equals(sessionFromcche)) {
                return jsonResponse.build("CP-04");
            }

            check.setFirstName(c.getFirstName());
            check.setLastName(c.getLastName());
            check.setGender(c.getGender());
            check.setDob(c.getDob());
            memberService.update(check);
            return jsonResponse.build("RC-00");

        }catch (Exception e){
            e.printStackTrace();
            return jsonResponse.build("RC-99");
        }
    }



    public String profile(String request , HttpHeaders header) {
        logger.info("incoming reuqest  Profile : " + request);
        logger.info("incoming reuqest  Profile  : " + header);
        String sessionFromHeader = header.get("sessionId").get(0).toString();
        logger.info("sessionFromHeader : " + sessionFromHeader);
        try {
            Gson gson = new Gson();
            Member c = gson.fromJson( request , Member.class );
            String sessionFromcche  = sessionConcurrentHashMap.getSession(c.getPhone()+ c.getDeviceId().toString());
            logger.info("Check session : " + sessionFromcche);

            Member check = memberService.getCustomerByPhone(c.getPhone());
            if (check == null) {
                return jsonResponse.build("CP-01");
            }

            if (sessionFromHeader == null || sessionFromHeader.isEmpty()) {
                return jsonResponse.build("CP-04");
            }

            if (!sessionFromHeader.equals(sessionFromcche)) {
                return jsonResponse.build("CP-04");
            }

           return gson.toJson(check);

        }catch (Exception e){
            e.printStackTrace();
            return jsonResponse.build("RC-99");
        }
    }



}