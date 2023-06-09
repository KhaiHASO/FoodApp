package com.example.foodblackpinkapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodblackpinkapp.constant.Constant;
import com.example.foodblackpinkapp.database.ApiService;
import com.example.foodblackpinkapp.database.RetrofitBase;
import com.example.foodblackpinkapp.databinding.ItemCartBinding;
import com.example.foodblackpinkapp.model.CartProductViewDTO;
import com.example.foodblackpinkapp.model.Customer;
import com.example.foodblackpinkapp.sharereferrences.ShareRefManager;
import com.example.foodblackpinkapp.utils.GlideUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartProductViewDTO> mListCartProducts;
    private final IClickListener iClickListener;

    public interface IClickListener {
        void clickDeteteFood(CartProductViewDTO cartProductView, int position);

        void updateItemFood(CartProductViewDTO cartProductView, int position);
    }

    public CartAdapter(List<CartProductViewDTO> mListCartProducts, IClickListener iClickListener) {
        this.mListCartProducts = mListCartProducts;
        this.iClickListener = iClickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding itemCartBinding = ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartProductViewDTO cartProductView = mListCartProducts.get(position);
        if (cartProductView == null) {
            return;
        }
        GlideUtils.loadUrl(cartProductView.getImage(), holder.mItemCartBinding.imgFoodCart);
        holder.mItemCartBinding.tvFoodNameCart.setText(cartProductView.getName());
        final String[] strFoodPriceCart = {cartProductView.getPrice() + Constant.CURRENCY};
        holder.mItemCartBinding.tvFoodPriceCart.setText(strFoodPriceCart[0]);
        holder.mItemCartBinding.tvCount.setText(String.valueOf(cartProductView.getQuantity()));
        holder.mItemCartBinding.tvSubtract.setOnClickListener(v -> {
            if (Integer.parseInt(holder.mItemCartBinding.tvCount.getText().toString()) == 1)
                return;
            int newCount = Integer.parseInt(holder.mItemCartBinding.tvCount.getText().toString()) - 1;
            holder.mItemCartBinding.tvCount.setText(String.valueOf(newCount));

            int totalPrice = (cartProductView.getPrice() / cartProductView.getQuantity()) * newCount;
            cartProductView.setQuantity(newCount);
            cartProductView.setPrice(totalPrice);
            // Cập nhật giá trị của strFoodPriceCart
            strFoodPriceCart[0] = totalPrice + Constant.CURRENCY;
            holder.mItemCartBinding.tvFoodPriceCart.setText(strFoodPriceCart[0]);
            iClickListener.updateItemFood(cartProductView, holder.getAdapterPosition());
            updateCart(cartProductView);
        });

        holder.mItemCartBinding.tvAdd.setOnClickListener(v -> {
            int newCount = Integer.parseInt(holder.mItemCartBinding.tvCount.getText().toString()) + 1;
            holder.mItemCartBinding.tvCount.setText(String.valueOf(newCount));
            int totalPrice = (cartProductView.getPrice() / cartProductView.getQuantity()) * newCount;
            cartProductView.setQuantity(newCount);
            cartProductView.setPrice(totalPrice);
            // Cập nhật giá trị của strFoodPriceCart
            strFoodPriceCart[0] = totalPrice + Constant.CURRENCY;
            holder.mItemCartBinding.tvFoodPriceCart.setText(strFoodPriceCart[0]);
            iClickListener.updateItemFood(cartProductView, holder.getAdapterPosition());
            updateCart(cartProductView);
        });
        holder.mItemCartBinding.tvDelete.setOnClickListener(v
                -> iClickListener.clickDeteteFood(cartProductView, holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return mListCartProducts == null ? 0 : mListCartProducts.size();
    }

    public void setData(List<CartProductViewDTO> cartProducts) {
        mListCartProducts = cartProducts;
        notifyDataSetChanged();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        private final ItemCartBinding mItemCartBinding;
        public CartViewHolder(ItemCartBinding itemCartBinding) {
            super(itemCartBinding.getRoot());
            this.mItemCartBinding = itemCartBinding;
        }
    }

    private void updateCart(CartProductViewDTO cartProductViewDTO) {
        ApiService mApiService = RetrofitBase.getInstance();
        Log.i("Cart Update", "Cart Product View DTO: " + cartProductViewDTO.toString());
        Call<Void> call = mApiService.updateCart(cartProductViewDTO);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Xử lý thành công
                    Log.i("Cart Update", "Cart successfully updated.");
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
}
