package com.example.paginator.googlepaginator.paginator;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.paginator.googlepaginator.R;
import com.example.paginator.googlepaginator.model.ImageItemDto;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
public class ImagesAdapter extends PagedListAdapter<ImageItemDto, ImagesAdapter.GalleryImageViewHolder> {


    public ImagesAdapter(@NonNull DiffUtil.ItemCallback diffCallback) {
        super(diffCallback);
    }

    public ImagesAdapter(@NonNull AsyncDifferConfig config) {
        super(config);
    }

/*    public void setItems(List<ImageItemDto> items) {
        notifyDataSetChanged();
    }

    public void addItems(List<ImageItemDto> items) {
        if (items.size() != 0) {
            this.items.addAll(items);
            notifyDataSetChanged();
        }
    }*/

    @Override
    public ImagesAdapter.GalleryImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImagesAdapter.GalleryImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryImageViewHolder holder, int position) {
        ImageItemDto imageItemDto = getItem(position);

        if (imageItemDto != null) {
            holder.categoryText.setText(imageItemDto.getName());
            Glide.with(holder.image.getContext())
                    .load(imageItemDto.getUrl());
        }
    }

/*    @Override
    public int getItemCount() {
        return items.size();
    }*/

    class GalleryImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        protected ImageView image;

        @BindView(R.id.image_name_text)
        protected TextView categoryText;

        public GalleryImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}