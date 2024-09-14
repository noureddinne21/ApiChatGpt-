package com.nouroeddinne.chatgpt4;

public interface CallbackResponse {

    void onSuccess(String text);
    void onError(Exception e);

}
