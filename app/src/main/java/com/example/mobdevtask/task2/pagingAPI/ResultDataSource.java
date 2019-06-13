package com.example.mobdevtask.task2.pagingAPI;

import com.example.mobdevtask.task2.apimodels.RandomUser;
import com.example.mobdevtask.task2.apimodels.Result;
import com.example.mobdevtask.task2.network.RetrofitCall;
import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultDataSource extends PageKeyedDataSource<Integer, Result> {



    private static final int FIRST_PAGE = 1;





    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Result> callback) {
        RetrofitCall.getInstance().getRandomUserAPI().getUsersPage(FIRST_PAGE, params.requestedLoadSize)
                .enqueue(new Callback<RandomUser>() {
                    @Override
                    public void onResponse(Call<RandomUser> call, Response<RandomUser> response) {
                        if(response.body() != null) {
                            callback.onResult(response.body().getResults(), null, response.body().getInfo().getPage() + 1);

                        }
                    }

                    @Override
                    public void onFailure(Call<RandomUser> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Result> callback) {


    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Result> callback) {
        RetrofitCall.getInstance().getRandomUserAPI().getUsersPage(params.key, params.requestedLoadSize)
                .enqueue(new Callback<RandomUser>() {
                    @Override
                    public void onResponse(Call<RandomUser> call, Response<RandomUser> response) {
                        if(response.body() != null) {
                            long nextKey = (params.key == response.body().getInfo().getResults())? null : params.key +1;

                            callback.onResult(response.body().getResults(), (int)nextKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<RandomUser> call, Throwable t) {

                    }
                });
    }

}
