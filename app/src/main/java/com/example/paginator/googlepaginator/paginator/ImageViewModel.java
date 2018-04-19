package com.example.paginator.googlepaginator.paginator;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.paginator.googlepaginator.model.ImageItemDto;
import com.example.paginator.googlepaginator.paginator.state.NetworkState;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
public class ImageViewModel extends ViewModel {
    public LiveData<PagedList<ImageItemDto>> userList;
    public LiveData<NetworkState> networkState;
    Executor executor;
    LiveData<ItemKeyedImageDataSource> tDataSource;

    public ImageViewModel() {
        executor = Executors.newFixedThreadPool(5);
        ImageDataSourceFactory imageDataSourceFactory = new ImageDataSourceFactory();

        tDataSource = imageDataSourceFactory.getMutableLiveData();

        networkState = Transformations.switchMap(imageDataSourceFactory.getMutableLiveData(), new Function<ItemKeyedImageDataSource, LiveData<NetworkState>>() {
            @Override
            public LiveData<NetworkState> apply(ItemKeyedImageDataSource input) {
                return input.getNetworkState();
            }
        });

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(5)
                        .setPageSize(10).build();

        userList = (new LivePagedListBuilder(imageDataSourceFactory, pagedListConfig))
                .setBackgroundThreadExecutor(executor)
                .build();
    }
}
