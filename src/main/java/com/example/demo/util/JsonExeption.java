package com.example.demo.util;


import com.google.gson.Gson;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;


@JsonIgnoreProperties({"cause", "localizedMessage", "stackTrace", "isoLanguage", "errorCode", "suppressed"})
public class JsonExeption extends RuntimeException implements Serializable{

    Logger logger = Logger.getLogger(JsonExeption.class.getName());

    private String errorCode;
    private String responseCode;
    private String responseDescription;

    public JsonExeption(String errorCode) {
        super();
        this.errorCode = errorCode;

        translateErrorToResponse();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    private void translateErrorToResponse() {
        ResourceBundle bundle;
        logger.info("GET PROP");
        try {
            ResourceBundle.clearCache();
            bundle = ResourceBundle.getBundle("error", new Locale("ind"));


        } catch (MissingResourceException e) {
            e.printStackTrace();
            return;
        }

        try {
            logger.info("ERROR SET : " + this.errorCode);
            String message = bundle.getString(this.errorCode);
            this.responseCode = this.errorCode.substring(this.errorCode.length()-2, this.errorCode.length());
            logger.info("ERROR SET DESC : " + message);
            this.responseDescription = message;
            Map<String , Object> result = new HashMap<String , Object>();
            result.put("status" , this.responseCode);
            result.put("statusDesc" , this.responseDescription);
            Gson gson = new Gson();


        } catch (MissingResourceException e) {
            this.responseCode = this.errorCode.substring(this.errorCode.length()-2, this.errorCode.length());
            this.responseDescription = "Undefined Error Code (" + this.errorCode + ")";
        }

    }
}