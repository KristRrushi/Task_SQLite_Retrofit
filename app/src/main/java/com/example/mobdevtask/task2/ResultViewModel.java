package com.example.mobdevtask.task2;

import com.example.mobdevtask.task2.apimodels.Result;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class ResultViewModel extends ViewModel {

    LiveData<PagedList<Result>> resultPageList;
    LiveData<PageKeyedDataSource<Integer, Result>> liveData;

    public ResultViewModel() {
        ResultDataSourceFactory resultDataSourceFactory = new ResultDataSourceFactory();

        liveData = resultDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pageListConfig =
                (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(ResultDataSource.RESULT)
                .build();

        resultPageList = (new LivePagedListBuilder(resultDataSourceFactory, pageListConfig)).build();
    }

}
