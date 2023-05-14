package com.example.foodblackpinkapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodblackpinkapp.adapter.CartAdapter;
import com.example.foodblackpinkapp.constant.Constant;
import com.example.foodblackpinkapp.database.ApiService;
import com.example.foodblackpinkapp.database.RetrofitBase;
import com.example.foodblackpinkapp.databinding.FragmentCartBinding;
import com.example.foodblackpinkapp.model.CartProductViewDTO;
import com.example.foodblackpinkapp.model.Product;
import com.example.foodblackpinkapp.sharereferrences.ShareRefManager;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends BaseFragment {

    private FragmentCartBinding mFragmentCartBinding;
    private CartAdapter mCartAdapter;
    private List<CartProductViewDTO> mListCartProduct;
    private int mAmount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentCartBinding = FragmentCartBinding.inflate(inflater, container, false);

        displayListFoodInCart();
        mFragmentCartBinding.tvOrderCart.setOnClickListener(v -> onClickOrderCart());

        return mFragmentCartBinding.getRoot();
    }

    @Override
    protected void initToolbar() {
        // Cài đặt toolbar
    }

    private void displayListFoodInCart() {
        // Hiển thị danh sách sản phẩm trong giỏ hàng
        if (getActivity() == null) {
            return;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mFragmentCartBinding.rcvFoodCart.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mFragmentCartBinding.rcvFoodCart.addItemDecoration(itemDecoration);

        initDataFoodCart();
    }

    private void initDataFoodCart() {
        String customerId = ShareRefManager.getInstance(requireContext().getApplicationContext()).getCustomer().getCustomerId();
        Log.d("CUSID", customerId);
        ApiService apiService = RetrofitBase.getInstance();
        Call<List<CartProductViewDTO>> call = apiService.getCartProductsByCustomerId(customerId);
        call.enqueue(new Callback<List<CartProductViewDTO>>() {
            @Override
            public void onResponse(Call<List<CartProductViewDTO>> call, Response<List<CartProductViewDTO>> response) {
                if (response.isSuccessful()) {
                    mListCartProduct = response.body();
                    if (mListCartProduct != null && !mListCartProduct.isEmpty()) {
                        // Xử lý khi nhận được danh sách CartProductViewDTO từ API

                        // Khởi tạo và gán adapter mới
                        mCartAdapter = new CartAdapter(mListCartProduct, new CartAdapter.IClickListener() {
                            @Override
                            public void clickDeteteFood(CartProductViewDTO cartProductView, int position) {
                                deleteFoodFromCart(cartProductView,position);


                            }

                            @Override
                            public void updateItemFood(CartProductViewDTO cartProductView, int position) {
                                calculateTotalPrice();

                            }
                        });
                        mFragmentCartBinding.rcvFoodCart.setAdapter(mCartAdapter);

                        calculateTotalPrice();
                    }
                } else {
                    // Xử lý khi yêu cầu API thất bại
                    Log.d("CartError", "Failed to retrieve cart products");
                }
            }

            @Override
            public void onFailure(Call<List<CartProductViewDTO>> call, Throwable t) {
                // Xử lý khi yêu cầu API gặp lỗi
                Log.d("CartError", "API request failed: " + t.getMessage());
            }
        });
    }


    private void clearCart() {
        // Xóa giỏ hàng
    }

    private void calculateTotalPrice() {
        // Tính toán tổng giá trị đơn hàng
        //List<Food> listFoodCart = FoodDatabase.getInstance(getActivity()).foodDAO().getListFoodCart();
        if (mListCartProduct == null || mListCartProduct.isEmpty()) {
            String strZero = 0 + Constant.CURRENCY;
            mFragmentCartBinding.tvTotalPrice.setText(strZero);
            mAmount = 0;
            return;
        }

        int totalPrice = 0;
        for (CartProductViewDTO productcart : mListCartProduct) {
            totalPrice = totalPrice + productcart.getPrice();
        }

        String strTotalPrice = totalPrice + Constant.CURRENCY;
        mFragmentCartBinding.tvTotalPrice.setText(strTotalPrice);
        mAmount = totalPrice;
    }

    private void deleteFoodFromCart(CartProductViewDTO cartProductViewDTOm, int position) {
        ApiService mApiService = RetrofitBase.getInstance();
        Log.i("Cart Delete", "Cart Product View DTO: " + cartProductViewDTOm.toString());

        Call<Void> call = mApiService.deleteCart(cartProductViewDTOm.getCustomerId(),cartProductViewDTOm.getProductId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Xử lý thành công
                    Log.i("Cart Update", "Cart successfully updated.");
                    mListCartProduct.remove(position);
                    mCartAdapter.notifyItemRemoved(position);
                    calculateTotalPrice();
                } else {
                    // Xử lý lỗi
                    String errorBody = null;
                    if (response.errorBody() != null) {
                        try {
                            errorBody = response.errorBody().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.e("Cart Update", "Failed to update cart. Server response: " + response.code() + ", Error body: " + errorBody);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Xử lý lỗi kết nối
                Log.e("Cart Update", "Error updating cart.", t);
                if (t != null) {
                    Log.e("Cart Update", "Error message: " + t.getMessage());
                }
            }
        });
    }


    public void onClickOrderCart() {
        // Xử lý sự kiện khi người dùng nhấp vào nút Đặt hàng
    }

    private String getStringListFoodsOrder() {
        // Lấy danh sách sản phẩm trong đơn hàng dạng chuỗi
        return null;
    }

    private Handler mHandler = new Handler();
    private Runnable mRefreshRunnable = new Runnable() {
        @Override
        public void run() {
            initDataFoodCart(); // Lấy lại dữ liệu
            mHandler.postDelayed(this, 5000); // Lặp lại sau 5 giây
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mHandler.post(mRefreshRunnable); // Bắt đầu làm mới khi Fragment hiển thị
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRefreshRunnable); // Dừng làm mới khi Fragment không hiển thị
    }

}
