package com.example.foodblackpinkapp.fragment;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodblackpinkapp.R;
import com.example.foodblackpinkapp.adapter.CartAdapter;
import com.example.foodblackpinkapp.constant.Constant;
import com.example.foodblackpinkapp.constant.GlobalFunction;
import com.example.foodblackpinkapp.database.ApiService;
import com.example.foodblackpinkapp.database.RetrofitBase;
import com.example.foodblackpinkapp.databinding.FragmentCartBinding;
import com.example.foodblackpinkapp.model.CartProductViewDTO;
import com.example.foodblackpinkapp.model.Customer;
import com.example.foodblackpinkapp.model.Order;
import com.example.foodblackpinkapp.model.Product;
import com.example.foodblackpinkapp.sharereferrences.ShareRefManager;
import com.example.foodblackpinkapp.utils.StringUtil;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

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
        if (mListCartProduct != null) {
            mListCartProduct.clear();
        }
        mCartAdapter.notifyDataSetChanged();
        calculateTotalPrice();
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
        Log.i("Cart Delete", "Cart Product View DTO: " + cartProductViewDTOm.toString());

        ApiService mApiService = RetrofitBase.getInstance();
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
        if (getActivity() == null) {
            return;
        }

        if (mListCartProduct == null || mListCartProduct.isEmpty()) {
            return;
        }
        Customer customer = ShareRefManager.getInstance(getActivity()).getCustomer();

        @SuppressLint("InflateParams") View viewDialog = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_order, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(viewDialog);
        bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);

        // init ui
        TextView tvFoodsOrder = viewDialog.findViewById(R.id.tv_foods_order);
        TextView tvPriceOrder = viewDialog.findViewById(R.id.tv_price_order);
        TextView edtNameOrder = viewDialog.findViewById(R.id.edt_name_order);
        TextView edtPhoneOrder = viewDialog.findViewById(R.id.edt_phone_order);
        TextView edtAddressOrder = viewDialog.findViewById(R.id.edt_address_order);
        TextView tvCancelOrder = viewDialog.findViewById(R.id.tv_cancel_order);
        TextView tvCreateOrder = viewDialog.findViewById(R.id.tv_create_order);

        // Set data
        tvFoodsOrder.setText(getStringListFoodsOrder());
        tvPriceOrder.setText(mFragmentCartBinding.tvTotalPrice.getText().toString());

        // Set default values for customer information
        edtNameOrder.setText(customer.getFullname());
        edtPhoneOrder.setText(customer.getPhone());
        edtAddressOrder.setText(""); // Để địa chỉ trống để người dùng nhập thông tin

        // Disable editing for customer information
        edtNameOrder.setEnabled(false);
        edtPhoneOrder.setEnabled(false);
        edtAddressOrder.setEnabled(true); // Cho phép người dùng chỉnh sửa địa chỉ giao hàng

        // Set listener
        tvCancelOrder.setOnClickListener(v -> bottomSheetDialog.dismiss());

        tvCreateOrder.setOnClickListener(v -> {
            String strName = edtNameOrder.getText().toString().trim();
            String strPhone = edtPhoneOrder.getText().toString().trim();
            String strAddress = edtAddressOrder.getText().toString().trim();
            // Tiếp tục xử lý việc tạo đơn hàng
            if (StringUtil.isEmpty(strName) || StringUtil.isEmpty(strPhone) || StringUtil.isEmpty(strAddress)) {
                GlobalFunction.showToastMessage(getActivity(), getString(R.string.message_enter_infor_order));
            } else {
                ApiService mApiService = RetrofitBase.getInstance();
                Call<Integer> call = mApiService.checkoutCart(customer.getCustomerId());
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()) {

                            Log.d("MA DON HANG",response.body().toString());
                            // Xử lý thành công
                            GlobalFunction.showToastMessage(getActivity(), getString(R.string.msg_order_success));
                            GlobalFunction.hideSoftKeyboard(getActivity());
                            bottomSheetDialog.dismiss();
                            clearCart();
                            Call<Void> callAddress =mApiService.updateOrderAddress(response.body(), strAddress);
                            callAddress.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                }
                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                }
                            });
                        } else {
                            // Xử lý lỗi
                            GlobalFunction.showToastMessage(getActivity(), getString(R.string.msg_order_success));
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
                    public void onFailure(Call<Integer> call, Throwable t) {
                        // Xử lý lỗi kết nối
                        Log.e("Cart Update", "Error updating cart.", t);
                        if (t != null) {
                            Log.e("Cart Update", "Error message: " + t.getMessage());
                        }
                    }
                });

            }

        });
        bottomSheetDialog.show();
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
