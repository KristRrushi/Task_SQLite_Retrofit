package com.example.mobdevtask.task2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.mobdevtask.R;
import com.example.mobdevtask.task2.adapter.ItemPageAdapter;
import com.example.mobdevtask.task2.apimodels.Result;
import com.example.mobdevtask.task2.pagingAPI.ResultDataSource;
import com.example.mobdevtask.task2.pagingAPI.ResultDataSourceFactory;
import com.example.mobdevtask.task2.viewmodel.ResultViewModel;

import java.util.Observable;


public class Task2Fragment extends Fragment {

    private RecyclerView recyclerView;
    private Button buttonRefresh;
    ItemPageAdapter itemPageAdapter;
    ResultViewModel resultViewModel;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        itemPageAdapter = new ItemPageAdapter(getContext());
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        resultViewModel.getResultPageList().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                itemPageAdapter.submitList(results);
            }
        });



    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_2, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(itemPageAdapter);
        buttonRefresh = view.findViewById(R.id.btn_refresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultViewModel.refresh();
            }
        });

        return view;
    }




}
