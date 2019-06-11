package com.example.mobdevtask.task2;

import com.example.mobdevtask.task2.apimodels.Result;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class ResultDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Result>> itemLiveDataSource = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Integer, Result> create() {
        ResultDataSource resultDataSource = new ResultDataSource();

        itemLiveDataSource.postValue(resultDataSource);

        return resultDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Result>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
