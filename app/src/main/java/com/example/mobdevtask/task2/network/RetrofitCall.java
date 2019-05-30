package com.example.mobdevtask.task2.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCall {

    private static final String BASE_URL = "https://randomuser.me";
    private RandomUserAPI randomUserAPI;
    private static RetrofitCall instance;


    public RetrofitCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        randomUserAPI = retrofit.create(RandomUserAPI.class);

    }
    
    public static RetrofitCall getInstance() {
        if(instance == null) {
            instance = new RetrofitCall();
        }
        return instance;
    }


    public RandomUserAPI getRandomUserAPI() {
        return randomUserAPI;
    }





}
