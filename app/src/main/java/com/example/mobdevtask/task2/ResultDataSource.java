package com.example.mobdevtask.task2;

import com.example.mobdevtask.task2.apimodels.RandomUser;
import com.example.mobdevtask.task2.apimodels.Result;
import com.example.mobdevtask.task2.network.RetrofitCall;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultDataSource extends PageKeyedDataSource<Integer, Result> {


    public static final int RESULT = 5;

    private static final int FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Result> callback) {
        RetrofitCall.getInstance().getRandomUserAPI().getUsersPage(FIRST_PAGE, RESULT)
                .enqueue(new Callback<RandomUser>() {
                    @Override
                    public void onResponse(Call<RandomUser> call, Response<RandomUser> response) {
                        if(response.body() != null) {
                            callback.onResult(response.body().getResults(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<RandomUser> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Result> callback) {
        RetrofitCall.getInstance().getRandomUserAPI().getUsersPage(params.key, RESULT)
                .enqueue(new Callback<RandomUser>() {
                    @Override
                    public void onResponse(Call<RandomUser> call, Response<RandomUser> response) {
                        Integer adKey = (params.key > 1) ? params.key - 1 : null;
                        if(response.body() != null) {
                            callback.onResult(response.body().getResults(), adKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<RandomUser> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Result> callback) {
        RetrofitCall.getInstance().getRandomUserAPI().getUsersPage(params.key, RESULT)
                .enqueue(new Callback<RandomUser>() {
                    @Override
                    public void onResponse(Call<RandomUser> call, Response<RandomUser> response) {
                        if(response.body() != null) {
                            callback.onResult(response.body().getResults(), params.key + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<RandomUser> call, Throwable t) {

                    }
                });
    }
}
