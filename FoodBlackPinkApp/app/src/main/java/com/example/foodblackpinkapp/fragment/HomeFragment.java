package com.example.foodblackpinkapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodblackpinkapp.R;
import com.example.foodblackpinkapp.activity.MainActivity;
import com.example.foodblackpinkapp.adapter.ProductAdapter;
import com.example.foodblackpinkapp.adapter.ProductGridAdapter;
import com.example.foodblackpinkapp.constant.Constant;
import com.example.foodblackpinkapp.constant.GlobalFunction;
import com.example.foodblackpinkapp.database.ApiService;
import com.example.foodblackpinkapp.database.RetrofitBase;
import com.example.foodblackpinkapp.databinding.FragmentHomeBinding;
import com.example.foodblackpinkapp.model.Product;
import com.example.foodblackpinkapp.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding mFragmentHomeBinding;

    private List<Product> mListProduct;
    private List<Product> mListProductPopular;
    private ProductAdapter mProductAdapter;
    private ProductGridAdapter mProductGridAdapter;
    private ApiService mApiService;

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
                    //displayListProductPopular();
                    for (Product product : mListProduct) {
                        Log.d("HomeFragment", "Product: " + product.toString());
                    }
                    displayListProductSuggest();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                GlobalFunction.showToastMessage(getActivity(), getString(R.string.msg_get_data_error));
            }
        });
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