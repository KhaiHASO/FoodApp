package com.example.foodblackpinkapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodblackpinkapp.R;
import com.example.foodblackpinkapp.activity.MainActivity;
import com.example.foodblackpinkapp.adapter.CartAdapter;
import com.example.foodblackpinkapp.constant.Constant;
import com.example.foodblackpinkapp.constant.GlobalFunction;
import com.example.foodblackpinkapp.databinding.FragmentCartBinding;
import com.example.foodblackpinkapp.model.Order;
import com.example.foodblackpinkapp.model.Product;
import com.example.foodblackpinkapp.utils.StringUtil;
import com.example.foodblackpinkapp.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends BaseFragment {

    private FragmentCartBinding mFragmentCartBinding;
    private CartAdapter mCartAdapter;
    private List<Product> mListFoodCart;
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
    }

    private void initDataFoodCart() {
        // Khởi tạo dữ liệu sản phẩm trong giỏ hàng
    }

    private void clearCart() {
        // Xóa giỏ hàng
    }

    private void calculateTotalPrice() {
        // Tính toán tổng giá trị đơn hàng
    }

    private void deleteFoodFromCart(Product food, int position) {
        // Xóa sản phẩm khỏi giỏ hàng
    }

    public void onClickOrderCart() {
        // Xử lý sự kiện khi người dùng nhấp vào nút Đặt hàng
    }

    private String getStringListFoodsOrder() {
        // Lấy danh sách sản phẩm trong đơn hàng dạng chuỗi
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Hủy đăng ký EventBus (nếu có)
    }
}
