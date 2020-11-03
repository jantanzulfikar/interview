package com.example.demo.model;



import java.io.Serializable;
import java.util.Date;

public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;
    long id;
    long idSender;
    long idReciver;
    String text;
    int type;
    Date created;
    Boolean status;
    Date updated;


    


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdSender() {
        return idSender;
    }

    public void setIdSender(long idSender) {
        this.idSender = idSender;
    }

    public long getIdReciver() {
        return idReciver;
    }

    public void setIdReciver(long idReciver) {
        this.idReciver = idReciver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}