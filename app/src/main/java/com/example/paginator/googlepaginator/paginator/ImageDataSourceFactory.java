package com.example.paginator.googlepaginator.paginator;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
public class ImageDataSourceFactory implements DataSource.Factory {
    MutableLiveData<ItemKeyedImageDataSource> mutableLiveData;
    ItemKeyedImageDataSource itemKeyedImageDataSource;

    public ImageDataSourceFactory() {
        this.mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        itemKeyedImageDataSource = new ItemKeyedImageDataSource();
        mutableLiveData.postValue(itemKeyedImageDataSource);
        return itemKeyedImageDataSource;
    }

    public MutableLiveData<ItemKeyedImageDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
