package com.example.foodblackpinkapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.foodblackpinkapp.fragment.AccountFragment;
import com.example.foodblackpinkapp.fragment.CartFragment;
import com.example.foodblackpinkapp.fragment.HomeFragment;
import com.example.foodblackpinkapp.fragment.OrderFragment;
import com.example.foodblackpinkapp.fragment.TrendFragment;
import com.example.foodblackpinkapp.fragment.TrendingFragment;

public class MainViewPagerAdapter extends FragmentStateAdapter {

    public MainViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();

            case 1:
                return new CartFragment();

            case 2:
                return new TrendFragment();

            case 3:
                return new OrderFragment();

            case 4:
                return new AccountFragment();

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
