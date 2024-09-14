package com.nouroeddinne.chatgpt4;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers({
            "x-rapidapi-key: cd947def00mshf2f8e21fff017ebp13720fjsn8e061a1c961d",
            "x-rapidapi-host: chatgpt-42.p.rapidapi.com",
            "Content-Type: application/json"
    })
    @POST("chatgpt")
    Call<ResponseBody> sendChatRequest(@Body RequestBody body);
}
