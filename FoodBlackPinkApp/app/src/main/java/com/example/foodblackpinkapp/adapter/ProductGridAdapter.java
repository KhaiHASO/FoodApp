package com.example.foodblackpinkapp.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodblackpinkapp.constant.Constant;
import com.example.foodblackpinkapp.databinding.ItemProductGridBinding;
import com.example.foodblackpinkapp.listener.IOnClickProductItemListener;
import com.example.foodblackpinkapp.model.Product;
import com.example.foodblackpinkapp.utils.GlideUtils;

import java.util.List;

public class ProductGridAdapter extends RecyclerView.Adapter<ProductGridAdapter.ProductGridViewHolder> {

    private final List<Product> mListProducts;
    public final IOnClickProductItemListener iOnClickProductItemListener;

    public ProductGridAdapter(List<Product> mListProducts, IOnClickProductItemListener iOnClickFoodItemListener) {
        this.mListProducts = mListProducts;
        this.iOnClickProductItemListener = iOnClickFoodItemListener;
    }

    @NonNull
    @Override
    public ProductGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductGridBinding itemProductGridBinding = ItemProductGridBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductGridViewHolder(itemProductGridBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductGridViewHolder holder, int position) {
        Product product = mListProducts.get(position);
        if (product == null) {
            return;
        }
        GlideUtils.loadUrl(product.getImage(), holder.mItemProductGridBinding.imgProduct);
        if (product.getDiscount() <= 0) {
            holder.mItemProductGridBinding.tvSaleOff.setVisibility(View.GONE);
            holder.mItemProductGridBinding.tvPrice.setVisibility(View.GONE);

            String strPrice = product.getPrice() + Constant.CURRENCY;
            holder.mItemProductGridBinding.tvPriceSale.setText(strPrice);
        } else {
            holder.mItemProductGridBinding.tvSaleOff.setVisibility(View.VISIBLE);
            holder.mItemProductGridBinding.tvPrice.setVisibility(View.VISIBLE);

            String strSale = "Giảm " + product.getDiscount() + "%";
            holder.mItemProductGridBinding.tvSaleOff.setText(strSale);

            String strOldPrice = product.getPrice() + Constant.CURRENCY;
            holder.mItemProductGridBinding.tvPrice.setText(strOldPrice);
            holder.mItemProductGridBinding.tvPrice.setPaintFlags(holder.mItemProductGridBinding.tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            String strRealPrice = product.getRealPrice() + Constant.CURRENCY;
            holder.mItemProductGridBinding.tvPriceSale.setText(strRealPrice);
        }
        holder.mItemProductGridBinding.tvFoodName.setText(product.getName());

        holder.mItemProductGridBinding.layoutItem.setOnClickListener(v -> iOnClickProductItemListener.onClickItemProduct(product));
    }

    @Override
    public int getItemCount() {
        return null == mListProducts ? 0 : mListProducts.size();
    }

    public static class ProductGridViewHolder extends RecyclerView.ViewHolder {

        private final ItemProductGridBinding mItemProductGridBinding;

        public ProductGridViewHolder(ItemProductGridBinding itemProductGridBinding) {
            super(itemProductGridBinding.getRoot());
            this.mItemProductGridBinding = itemProductGridBinding;
        }
    }
}
