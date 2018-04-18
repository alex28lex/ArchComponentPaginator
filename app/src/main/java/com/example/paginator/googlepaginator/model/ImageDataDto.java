package com.example.paginator.googlepaginator.model;


import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
@Data
@Builder
@AllArgsConstructor

public class ImageDataDto {
    private int id;
    private List<ImageItemDto> items;
    private String prevCursor;
    private String nextCursor;

    public static DiffCallback<ImageDataDto> DIFF_CALLBACK = new DiffCallback<ImageDataDto>() {
        @Override
        public boolean areItemsTheSame(@NonNull ImageDataDto oldItem, @NonNull ImageDataDto newItem) {
            return oldItem.nextCursor == newItem.nextCursor;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ImageDataDto oldItem, @NonNull ImageDataDto newItem) {
            return oldItem.equals(newItem);
        }
    };

}
