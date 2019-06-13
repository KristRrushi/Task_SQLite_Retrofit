package com.example.mobdevtask.task2.viewmodel;

import com.example.mobdevtask.task2.apimodels.Result;
import com.example.mobdevtask.task2.pagingAPI.ResultDataSource;
import com.example.mobdevtask.task2.pagingAPI.ResultDataSourceFactory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class ResultViewModel extends ViewModel {

    LiveData<PagedList<Result>> resultPageList;
    ResultDataSourceFactory resultDataSourceFactory;


    public ResultViewModel() {
        resultDataSourceFactory = new ResultDataSourceFactory();
        PagedList.Config pageListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20)
                        .build();

        resultPageList = (new LivePagedListBuilder(resultDataSourceFactory, pageListConfig)).build();
    }

    public LiveData<PagedList<Result>> getResultPageList() {
        return resultPageList;
    }

    public void refresh() {

        resultDataSourceFactory.getItemLiveDataSource().getValue().invalidate();

    }



}
