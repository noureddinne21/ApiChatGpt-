package com.nouroeddinne.chatgpt4;

public class ModelMsg {

    private String message,from;


    public ModelMsg(String message, String from) {
        this.message = message;
        this.from = from;
    }

    public ModelMsg() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
