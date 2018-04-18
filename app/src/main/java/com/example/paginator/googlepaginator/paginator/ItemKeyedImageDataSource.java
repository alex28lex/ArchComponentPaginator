package com.example.paginator.googlepaginator.paginator;


import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.ItemKeyedDataSource;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.paginator.googlepaginator.dataprovider.ImagesDataProvider;
import com.example.paginator.googlepaginator.dataprovider.ImagesRestMockDataProvider;
import com.example.paginator.googlepaginator.model.ImageDataDto;
import com.example.paginator.googlepaginator.paginator.state.NetworkState;
import com.example.paginator.googlepaginator.paginator.state.Status;

import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Developed by Magora Team (magora-systems.com). 2018.
 *
 * @author mihaylov
 */
public class ItemKeyedImageDataSource extends PageKeyedDataSource<String, ImageDataDto> {
    private ImagesDataProvider imagesDataProvider;
    ItemKeyedDataSource.LoadInitialParams<String> initialParams;
    ItemKeyedDataSource.LoadParams<String> afterParams;
    private MutableLiveData networkState;
    private MutableLiveData initialLoading;

    public ItemKeyedImageDataSource() {
        this.imagesDataProvider = new ImagesRestMockDataProvider();
        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, ImageDataDto> callback) {
        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);
        imagesDataProvider.getImagesData(null, params.requestedLoadSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ImageDataDto>() {
                    @Override
                    public void accept(ImageDataDto imageDataDto) throws Exception {
                        callback.onResult(Collections.singletonList(imageDataDto));
                        initialLoading.postValue(NetworkState.LOADED);
                        networkState.postValue(NetworkState.LOADED);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        String errorMessage;
                        errorMessage = t.getMessage();
                        networkState.postValue(new NetworkState(Status.FAILED, errorMessage));
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, ImageDataDto> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull final LoadCallback<String, ImageDataDto> callback) {
        networkState.postValue(NetworkState.LOADING);
        imagesDataProvider.getImagesData(params.key, params.requestedLoadSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ImageDataDto>() {
                    @Override
                    public void accept(ImageDataDto imageDataDto) throws Exception {
                        callback.onResult(imageDataDto);
                        networkState.postValue(NetworkState.LOADED);
                        afterParams = null;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        networkState.postValue(new NetworkState(Status.FAILED, throwable.getLocalizedMessage()));
                    }
                });
    }

    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }

/*
    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull final LoadInitialCallback<ImageDataDto> callback) {
        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);
        imagesDataProvider.getImagesData(null, params.requestedLoadSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ImageDataDto>() {
                    @Override
                    public void accept(ImageDataDto imageDataDto) throws Exception {
                        callback.onResult(Collections.singletonList(imageDataDto));
                        initialLoading.postValue(NetworkState.LOADED);
                        networkState.postValue(NetworkState.LOADED);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        String errorMessage;
                        errorMessage = t.getMessage();
                        networkState.postValue(new NetworkState(Status.FAILED, errorMessage));
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull final LoadCallback<ImageDataDto> callback) {
        networkState.postValue(NetworkState.LOADING);
        imagesDataProvider.getImagesData(params.key, params.requestedLoadSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ImageDataDto>() {
                    @Override
                    public void accept(ImageDataDto imageDataDto) throws Exception {
                        callback.onResult(Collections.singletonList(imageDataDto));
                        networkState.postValue(NetworkState.LOADED);
                        afterParams = null;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        networkState.postValue(new NetworkState(Status.FAILED, throwable.getLocalizedMessage()));
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<ImageDataDto> callback) {

    }

    @NonNull
    @Override
    public String getKey(@NonNull ImageDataDto item) {
        return item.getNextCursor();
    }*/
}
