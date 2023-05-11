package com.example.foodblackpinkapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodblackpinkapp.databinding.ItemMoreImageBinding;
import com.example.foodblackpinkapp.model.ProductDetail;
import com.example.foodblackpinkapp.utils.GlideUtils;

import java.util.List;

public class MoreImageAdapter extends RecyclerView.Adapter<MoreImageAdapter.MoreImageViewHolder> {

    public MoreImageAdapter(List<ProductDetail> productDetails) {
        this.mListProductDetail = productDetails;
    }

    private final List<ProductDetail> mListProductDetail;

    @NonNull
    @Override
    public MoreImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMoreImageBinding itemMoreImageBinding = ItemMoreImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MoreImageViewHolder(itemMoreImageBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreImageViewHolder holder, int position) {
        ProductDetail productDetail = mListProductDetail.get(position);
        if (productDetail == null) {
            return;
        }
        GlideUtils.loadUrl(productDetail.getImage(), holder.mItemMoreImageBinding.imageFood);
    }

    @Override
    public int getItemCount() {
        return mListProductDetail == null ? 0 : mListProductDetail.size();
    }

    public static class MoreImageViewHolder extends RecyclerView.ViewHolder {

        private final ItemMoreImageBinding mItemMoreImageBinding;

        public MoreImageViewHolder(ItemMoreImageBinding itemMoreImageBinding) {
            super(itemMoreImageBinding.getRoot());
            this.mItemMoreImageBinding = itemMoreImageBinding;
        }
    }
}
