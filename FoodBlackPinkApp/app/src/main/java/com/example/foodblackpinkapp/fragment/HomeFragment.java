package com.example.foodblackpinkapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodblackpinkapp.R;
import com.example.foodblackpinkapp.adapter.ProductPopularAdapter;
import com.example.foodblackpinkapp.adapter.ProductGridAdapter;
import com.example.foodblackpinkapp.constant.Constant;
import com.example.foodblackpinkapp.constant.GlobalFunction;
import com.example.foodblackpinkapp.database.ApiService;
import com.example.foodblackpinkapp.database.RetrofitBase;
import com.example.foodblackpinkapp.databinding.FragmentHomeBinding;
import com.example.foodblackpinkapp.model.Product;
import com.example.foodblackpinkapp.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding mFragmentHomeBinding;

    private List<Product> mListProduct;
    private List<Product> mListProductPopular;
    private ProductPopularAdapter mProductPopularAdapter;
    private ProductGridAdapter mProductGridAdapter;
    private ApiService mApiService;

    private final Handler mHandlerBanner = new Handler();
    private final Runnable mRunnableBanner = new Runnable() {
        @Override
        public void run() {
            if (mListProductPopular == null || mListProductPopular.isEmpty()) {
                return;
            }
            if (mFragmentHomeBinding.viewpager2.getCurrentItem() == mListProductPopular.size() - 1) {
                mFragmentHomeBinding.viewpager2.setCurrentItem(0);
                return;
            }
            mFragmentHomeBinding.viewpager2.setCurrentItem(mFragmentHomeBinding.viewpager2.getCurrentItem() + 1);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        getListProductFromApi("");
        return mFragmentHomeBinding.getRoot();
    }

    private void getListProductFromApi(String key) {
        if (getActivity() == null) {
            return;
        }
        mApiService = RetrofitBase.getInstance();
        mApiService.getAllProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mFragmentHomeBinding.layoutContent.setVisibility(View.VISIBLE);
                    mListProduct = new ArrayList<>();
                    for (Product product : response.body()) {
                        if (StringUtil.isEmpty(key)) {
                            mListProduct.add(0, product);
                        } else {
                            if (GlobalFunction.getTextSearch(product.getName()).toLowerCase().trim()
                                    .contains(GlobalFunction.getTextSearch(key).toLowerCase().trim())) {
                                mListProduct.add(0, product);
                            }
                        }
                    }

                    for (Product product : mListProduct) {
                        Log.d("HomeFragment", "Product: " + product.toString());
                    }
                    displayListProductPopular();
                    displayListProductSuggest();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                GlobalFunction.showToastMessage(getActivity(), getString(R.string.msg_get_data_error));
            }
        });
    }

    private void displayListProductPopular() {
        mProductPopularAdapter = new ProductPopularAdapter(getListProductPopular(), this::goToFoodDetail);
        mFragmentHomeBinding.viewpager2.setAdapter(mProductPopularAdapter);
        mFragmentHomeBinding.indicator3.setViewPager(mFragmentHomeBinding.viewpager2);

        mFragmentHomeBinding.viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mHandlerBanner.removeCallbacks(mRunnableBanner);
                mHandlerBanner.postDelayed(mRunnableBanner, 3000);
            }
        });
    }

    private List<Product> getListProductPopular() {
        mListProductPopular = new ArrayList<>();
        if (mListProduct == null || mListProduct.isEmpty()) {
            return mListProductPopular;
        }
        for (Product product : mListProduct) {
                mListProductPopular.add(product);
        }
        return mListProductPopular;
    }

    private void displayListProductSuggest() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mFragmentHomeBinding.rcvProduct.setLayoutManager(gridLayoutManager);

        mProductGridAdapter = new ProductGridAdapter(mListProduct, this::goToFoodDetail);
        mFragmentHomeBinding.rcvProduct.setAdapter(mProductGridAdapter);
    }

    private void goToFoodDetail(@NonNull Product product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_INTENT_FOOD_OBJECT, product);
       // GlobalFunction.startActivity(getActivity(), FoodDetailActivity.class, bundle);
    }


    @Override
    protected void initToolbar() {

    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}