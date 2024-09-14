package com.nouroeddinne.chatgpt4;

public class ApiResponse {
    private String result;
    private boolean status;
    private int server_code;

    // Getters and Setters
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getServerCode() {
        return server_code;
    }

    public void setServerCode(int server_code) {
        this.server_code = server_code;
    }
}

