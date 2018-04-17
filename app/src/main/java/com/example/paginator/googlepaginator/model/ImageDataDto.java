package com.example.paginator.googlepaginator.model;


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

}
