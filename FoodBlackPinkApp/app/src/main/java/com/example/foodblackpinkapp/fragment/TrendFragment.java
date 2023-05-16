package com.example.foodblackpinkapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodblackpinkapp.R;
import com.example.foodblackpinkapp.adapter.VideoAdapter;
import com.example.foodblackpinkapp.database.ApiService;
import com.example.foodblackpinkapp.model.MessageVideo;
import com.example.foodblackpinkapp.model.Video;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendFragment extends Fragment {
    private ViewPager2 viewPager2;
    private VideoAdapter videosAdapter;
    private List<Video> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trend, container, false);
        viewPager2 = view.findViewById(R.id.viewpager);
        list = new ArrayList<>();
        getVideos();

        return view;
    }

    private void getVideos() {
        ApiService.servieapi.getVideos().enqueue(new Callback<MessageVideo>() {
            @Override
            public void onResponse(Call<MessageVideo> call, Response<MessageVideo> response) {
                if (response.isSuccessful()) {
                    list = response.body().getResult();
                    // Log.d("TAG", list.get(0).getUrl());
                    videosAdapter = new VideoAdapter(requireContext(), list);
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                    viewPager2.setAdapter(videosAdapter);
                } else {
                    Log.d("TAG", "Response is not successful.");
                }
            }

            @Override
            public void onFailure(Call<MessageVideo> call, Throwable t) {
                Log.d("TAG", t.getMessage());
            }
        });
    }
}
