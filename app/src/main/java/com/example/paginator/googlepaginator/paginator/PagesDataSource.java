package com.example.paginator.googlepaginator.paginator;


import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.paginator.googlepaginator.dataprovider.ImagesDataProvider;
import com.example.paginator.googlepaginator.dataprovider.ImagesRestMockDataProvider;
import com.example.paginator.googlepaginator.model.ImageDataDto;

import io.reactivex.functions.Consumer;


/**
 * Developed by Magora Team (magora-systems.com). 2018.
 *
 * @author mihaylov
 */
public class PagesDataSource extends PageKeyedDataSource<String, ImageDataDto> {
    private ImagesDataProvider imagesDataProvider;

    public PagesDataSource(ImagesDataProvider imagesDataProvider) {
        this.imagesDataProvider = new ImagesRestMockDataProvider();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, ImageDataDto> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, ImageDataDto> callback) {
        // ignored, since we only ever append to our initial load
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull final LoadCallback<String, ImageDataDto> callback) {
        imagesDataProvider.getImagesData(params.key, params.requestedLoadSize)
                .subscribe(new Consumer<ImageDataDto>() {
                    @Override
                    public void accept(ImageDataDto imageDataDto) throws Exception {
                        callback.onResult(imageDataDto,imageDataDto.getNextCursor());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
