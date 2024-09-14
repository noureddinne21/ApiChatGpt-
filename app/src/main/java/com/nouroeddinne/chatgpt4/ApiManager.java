package com.nouroeddinne.chatgpt4;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ApiManager {

    private ApiService apiService;

    public ApiManager() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public interface CallbackResponse {
        void onSuccess(String text);
        void onError(Exception e);
    }

    public void sendChatRequest(String jsonBody,CallbackResponse callbackResponse) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json"), jsonBody);

        Call<ResponseBody> call = apiService.sendChatRequest(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    //try {
                    String responseData = null;
                    try {
                        responseData = response.body().string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
//                        Gson gson = new Gson();
//                        ModelMsg model = gson.fromJson(responseData, ModelMsg.class);
                        callbackResponse.onSuccess(responseData);
//
                        System.out.println("Response: " + responseData);
//                        // Handle the response data here
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        CallbackResponse.onError(e);
//                    }
                } else {
                    callbackResponse.onError(new IOException("Request failed with code: " + response.code()));
                    //System.err.println("Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callbackResponse.onError(new IOException("Request failed: " + t.getMessage()));
                //t.printStackTrace();
                //System.err.println("Request failed: " + t.getMessage());
            }
        });
    }
}
