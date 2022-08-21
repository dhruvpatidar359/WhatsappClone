package com.example.whatsappclone.Models;

public class MessageModels {
    String uid,message;

    public MessageModels(String uid, String message) {
        this.uid = uid;
        this.message = message;
    }

    Long timestamp;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public MessageModels(String message, Long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public MessageModels(String uid, String message, Long timestamp) {
        this.uid = uid;
        this.message = message;
        this.timestamp = timestamp;
    }


}
