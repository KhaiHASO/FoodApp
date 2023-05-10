package com.example.foodblackpinkapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodblackpinkapp.utils.GlideUtils;
import com.example.foodblackpinkapp.databinding.ItemProductPopularBinding;
import com.example.foodblackpinkapp.listener.IOnClickProductItemListener;
import com.example.foodblackpinkapp.model.Product;

import java.util.List;

public class ProductPopularAdapter extends RecyclerView.Adapter<ProductPopularAdapter.ProductViewHolder> {

    private final List<Product> mListProducts;
    private final IOnClickProductItemListener iOnClickProductItemListener;

    public ProductPopularAdapter(List<Product> mListProducts, IOnClickProductItemListener iOnClickProductItemListener) {
        this.mListProducts = mListProducts;
        this.iOnClickProductItemListener = iOnClickProductItemListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductPopularBinding binding = ItemProductPopularBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = mListProducts.get(position);
        if (product == null) {
            return;
        }
        GlideUtils.loadUrlBanner(product.getImage(), holder.mBinding.imageProduct);
        if (product.getDiscount()<= 0) {
            holder.mBinding.tvSaleOff.setVisibility(View.GONE);
        } else {
            holder.mBinding.tvSaleOff.setVisibility(View.VISIBLE);
            String strSale = "Giảm " + product.getDiscount() + "%";
            holder.mBinding.tvSaleOff.setText(strSale);
        }
        holder.mBinding.layoutItem.setOnClickListener(v -> iOnClickProductItemListener.onClickItemProduct(product));
    }

    @Override
    public int getItemCount() {
        return mListProducts == null ? 0 : mListProducts.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        private final ItemProductPopularBinding mBinding;

        public ProductViewHolder(@NonNull ItemProductPopularBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}

