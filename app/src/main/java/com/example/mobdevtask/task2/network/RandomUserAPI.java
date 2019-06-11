package com.example.mobdevtask.task2.network;

import com.example.mobdevtask.task2.apimodels.RandomUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomUserAPI {

    @GET("/api/")
    Call<RandomUser> getUsers(@Query("results") int results);

    @GET("/api/")
    Call<RandomUser> getUsersPage(@Query("page") int page,
                                  @Query("results") int results);
}
