package com.example.demo.controller.impl;

import com.example.demo.model.Customer;
import com.example.demo.controller.CustomerController;
import com.example.demo.service.CustomerService;
import com.example.demo.util.JsonResponse;
import com.example.demo.util.SendMail;
import com.example.demo.util.WeakConcurrentHashMap;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import com.example.demo.util.generateMD5;
import org.apache.commons.lang3.*;


@RestController
public class CustomerControllerImpl implements CustomerController {

    Logger logger = Logger.getLogger(CustomerControllerImpl.class.getName());

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JsonResponse jsonResponse;

    @Autowired
    private SendMail sendMail;

    @Autowired
    private WeakConcurrentHashMap weakConcurrentHashMap;

    @Override
    public String registration(String request , HttpHeaders header){
        try {
            logger.info("incoming request register : " + request);
            logger.info("incoming request register : " + header.get("ads"));

            Gson gson = new Gson();
            Customer c = gson.fromJson( request , Customer.class );
            logger.info("name : " + c.getCustomerName());
            logger.info("phone : " + c.getCustomerPhone());
            if ( c.getCustomerName() == null || c.getCustomerPhone() == null) {
                return jsonResponse.build("RC-99");
            }

            Customer checkCust = customerService.getCustomerByPhone(c.getCustomerPhone());
            logger.info("Count Data : " + checkCust);
            if (checkCust != null) {
                return jsonResponse.build("RC-01");
            }

            int checkUserName = customerService.getCountCustomerByUserName(c.getUsername());
            if (checkUserName > 1 ) {
                return jsonResponse.build("RC-02");
            }

            String hashPassword = generateMD5.getMd5(c.getPassword());
            c.setPassword(hashPassword);
            customerService.save(c);
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
        logger.info("SATIAMA SENSEI");
        try {
            Gson gson = new Gson();
            Customer c = gson.fromJson( request , Customer.class );
            if (c.getUsername() == null || c.getPassword() == null) {
                return jsonResponse.build("LG-02");
            }

            String hashPass = generateMD5.getMd5(c.getPassword());
            Customer checkLogin = customerService.getLogin(c.getUsername());
            if (checkLogin == null){
               return jsonResponse.build("LG-03");
            }

            if (!hashPass.equals(checkLogin.getPassword())) {
                return jsonResponse.build("LG-03");
            }

            return gson.toJson(checkLogin);

        }catch (Exception e){
            e.printStackTrace();
            return jsonResponse.build("RC-99");
        }
    }


    @Override
    public String inquiryChangePassword(String request , HttpHeaders header) {
        logger.info("incoming reuqest Inquiry Change Password : " + request);
        logger.info("incoming reuqest Inquiry Change Password : " + header);
        try {
            Gson gson = new Gson();
            Customer c = gson.fromJson(request , Customer.class);
            Customer check = customerService.getCustomerByPhone(c.getCustomerPhone());
            if (check == null) {
                return jsonResponse.build("CP-01");
            }
            logger.info("check email : " + check.getEmail());
            String rnd = RandomStringUtils.randomNumeric(5);
            String body = "Hay " + check.getCustomerName() + "\n gunakan OTP  "+ rnd + " ini untuk mengganti password anda";
            logger.info( body);
            sendMail.send(check.getEmail() , "Request Ganti Password" , body );
            String key = check.getCustomerPhone()+check.getDeviceId();
            weakConcurrentHashMap.put(key , rnd);
            logger.info((String) weakConcurrentHashMap.get(key));
            return jsonResponse.build("RC-00");

        }catch (Exception e){
            e.printStackTrace();
            return jsonResponse.build("RC-99");
        }
    }


    public String postingChangePassword(String request , HttpHeaders header) {
        logger.info("incoming reuqest Posting Change Password : " + request);
        logger.info("incoming reuqest Posting Change Password : " + header);
        try {
            Gson gson = new Gson();
            JsonObject convertedObject = new Gson().fromJson(request, JsonObject.class);
            String phone = convertedObject.get("customerPhone").getAsString();
            String deviceId = convertedObject.get("deviceId").getAsString();
            String inputOtp = convertedObject.get("OTP").getAsString();
            String password = convertedObject.get("newPassword").getAsString();

            Customer check = customerService.getCustomerByPhone(phone);
            if (check == null) {
                return jsonResponse.build("CP-01");
            }

            String key = check.getCustomerPhone()+deviceId;
            String checkOTP = (String) weakConcurrentHashMap.get(key);
            logger.info(checkOTP);
            if (checkOTP == null) {
                return jsonResponse.build("CP-02");
            }

            if (!checkOTP.equals(inputOtp)) {
                return jsonResponse.build("CP-03");
            }

            check.setPassword(generateMD5.getMd5(password));
            customerService.update(check);

            return jsonResponse.build("RC-00");

        }catch (Exception e){
            e.printStackTrace();
            return jsonResponse.build("RC-99");
        }
    }


    public String updateProfile(String request , HttpHeaders header) {
        logger.info("incoming reuqest update Profile : " + request);
        logger.info("incoming reuqest update Profile  : " + header);
        try {
            Gson gson = new Gson();
            Customer c = gson.fromJson( request , Customer.class );
            Customer check = customerService.getCustomerByPhone(c.getCustomerPhone());
            if (check == null) {
                return jsonResponse.build("CP-01");
            }

            check.setEmail(c.getEmail());
            check.setAddress(c.getAddress());
            check.setCustomerName(c.getCustomerName());
            customerService.update(check);
            return jsonResponse.build("RC-00");

        }catch (Exception e){
            e.printStackTrace();
            return jsonResponse.build("RC-99");
        }
    }



}