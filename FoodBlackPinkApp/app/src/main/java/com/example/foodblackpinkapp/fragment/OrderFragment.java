package com.example.foodblackpinkapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodblackpinkapp.R;
import com.example.foodblackpinkapp.activity.MainActivity;
import com.example.foodblackpinkapp.adapter.OrderAdapter;
import com.example.foodblackpinkapp.database.ApiService;
import com.example.foodblackpinkapp.database.RetrofitBase;
import com.example.foodblackpinkapp.databinding.FragmentOrderBinding;
import com.example.foodblackpinkapp.model.BillViewDTO;
import com.example.foodblackpinkapp.sharereferrences.ShareRefManager;
import com.example.foodblackpinkapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFragment extends BaseFragment {

    private FragmentOrderBinding mFragmentOrderBinding;
    private List<BillViewDTO> mListOrder;
    private OrderAdapter mOrderAdapter;
    private Handler mHandler;
    private Runnable mRunnable;
    private static final long DELAY = 5000; // Độ trễ 5 giây

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentOrderBinding = FragmentOrderBinding.inflate(inflater, container, false);
        initView();
        startLoadingOrders();
        return mFragmentOrderBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopLoadingOrders();
    }

    @Override
    protected void initToolbar() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setToolBar(false, getString(R.string.order));
        }
    }

    private void initView() {
        if (getActivity() == null) {
            return;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mFragmentOrderBinding.rcvOrder.setLayoutManager(linearLayoutManager);

        mListOrder = new ArrayList<>();
        mOrderAdapter = new OrderAdapter(mListOrder);
        mFragmentOrderBinding.rcvOrder.setAdapter(mOrderAdapter);
    }

    public void getListOrders() {
        if (getActivity() == null) {
            return;
        }

        String customerId = ShareRefManager.getInstance(getActivity()).getCustomer().getCustomerId();

        ApiService mApiService = RetrofitBase.getInstance();
        Call<List<BillViewDTO>> call = mApiService.getBillByCustomerId(customerId);

        call.enqueue(new Callback<List<BillViewDTO>>() {
            @Override
            public void onResponse(Call<List<BillViewDTO>> call, Response<List<BillViewDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mListOrder.clear();
                    mListOrder.addAll(response.body());
                    mOrderAdapter.notifyDataSetChanged();
                } else {
                    Log.e("getListOrders", "Response from server is not successful");
                }
            }

            @Override
            public void onFailure(Call<List<BillViewDTO>> call, Throwable t) {
                Log.e("getListOrders", "Error calling API", t);
            }
        });
    }

    private void startLoadingOrders() {
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                getListOrders();
                mHandler.postDelayed(this, DELAY);
            }
        };
        mHandler.postDelayed(mRunnable, DELAY);
    }

    private void stopLoadingOrders() {
        if (mHandler != null && mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }
}
