package com.example.mobdevtask.task2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.example.mobdevtask.R;
import com.example.mobdevtask.task2.apimodels.RandomUser;
import com.example.mobdevtask.task2.apimodels.Result;
import com.example.mobdevtask.task2.adapter.UserAdapterAPI;
import com.example.mobdevtask.task2.network.RetrofitCall;

import java.util.List;




public class Task2Fragment extends Fragment {

    private UserAdapterAPI userAdapter;
    private RecyclerView recyclerView;
    private Button buttonRefresh;
    private int count = 1;
    private RetrofitCall retrofitCall;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userAdapter = new UserAdapterAPI();
        callApi();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_2, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(userAdapter);
        buttonRefresh = view.findViewById(R.id.btn_refresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                callApi();
            }
        });

        return view;
    }


    private void callApi() {

       // retrofitCall = new RetrofitCall().getInstance();
        RetrofitCall retrofitCall = RetrofitCall.getInstance();

        Call<RandomUser> call = retrofitCall.getRandomUserAPI().getUsers(count);

        call.enqueue(new Callback<RandomUser>() {
            @Override
            public void onResponse(Call<RandomUser> call, Response<RandomUser> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                List<Result> listResult = response.body().getResults();
                userAdapter.setUsers(listResult);
            }

            @Override
            public void onFailure(Call<RandomUser> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage().toUpperCase(), Toast.LENGTH_LONG ).show();
            }
        });

    }

}
