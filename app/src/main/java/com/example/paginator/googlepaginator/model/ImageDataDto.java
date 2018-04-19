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

    public static DiffCallback<ImageItemDto> DIFF_CALLBACK = new DiffCallback<ImageItemDto>() {
        @Override
        public boolean areItemsTheSame(@NonNull ImageItemDto oldItem, @NonNull ImageItemDto newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ImageItemDto oldItem, @NonNull ImageItemDto newItem) {
            return oldItem.equals(newItem);
        }
    };

}
