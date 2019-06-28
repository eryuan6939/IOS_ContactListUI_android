package com.example.chongeryuan.codingassignment.api;

import com.example.chongeryuan.codingassignment.models.ContactResponse;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface Api {

    @GET("users")
    Call<ContactResponse> GetContactList(
            //@Body Object params
            @QueryMap Map<String, String> params
            );

}
