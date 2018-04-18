package com.example.paginator.googlepaginator;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.paginator.googlepaginator.model.ImageDataDto;
import com.example.paginator.googlepaginator.paginator.ImageViewModel;
import com.example.paginator.googlepaginator.paginator.ImagesAdapter;
import com.example.paginator.googlepaginator.paginator.state.NetworkState;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @BindView(R.id.progress)
    protected ProgressBar progressBar;
    private ImagesAdapter imagesAdapter;
    ImageViewModel imageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        imageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
        imagesAdapter = new ImagesAdapter(ImageDataDto.DIFF_CALLBACK);

        imageViewModel.userList.observe(this, new Observer<PagedList<ImageDataDto>>() {
            @Override
            public void onChanged(@Nullable PagedList<ImageDataDto> imageDataDtos) {
                imagesAdapter.setList(imageDataDtos);
            }
        });

        imageViewModel.networkState.observe(this, new Observer<NetworkState>() {
            @Override
            public void onChanged(@Nullable NetworkState networkState) {
                imagesAdapter.setNetworkState(networkState);
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(imagesAdapter);

    }
}
