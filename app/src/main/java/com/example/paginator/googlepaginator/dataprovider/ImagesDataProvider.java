package com.example.paginator.googlepaginator.dataprovider;

import com.example.paginator.googlepaginator.model.ImageDataDto;

import io.reactivex.Flowable;

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
public interface ImagesDataProvider {
    Flowable<ImageDataDto> getImagesData(String nextCursor, int pageSize);
}
