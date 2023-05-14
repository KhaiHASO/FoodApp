package com.example.foodblackpinkapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodblackpinkapp.databinding.ItemCategoryBinding;
import com.example.foodblackpinkapp.listener.IOnClickCategoryItemListener;
import com.example.foodblackpinkapp.model.Category;
import com.example.foodblackpinkapp.utils.GlideUtils;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private final List<Category> mListCategories;
    private final IOnClickCategoryItemListener iOnClickCategoryItemListener;

    public CategoryAdapter(List<Category> mListCategories, IOnClickCategoryItemListener iOnClickCategoryItemListener) {
        this.mListCategories = mListCategories;
        this.iOnClickCategoryItemListener = iOnClickCategoryItemListener;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding itemCategoryBinding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryAdapter.CategoryViewHolder(itemCategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        Category category = mListCategories.get(position);
        if (category == null) {
            return;
        }
        GlideUtils.loadUrl(category.getCategoryImage(), holder.mItemCategoryBinding.imgCategory);
        holder.mItemCategoryBinding.tvFoodName.setText(category.getCategoryName());
        holder.mItemCategoryBinding.layoutItem.setOnClickListener(v -> iOnClickCategoryItemListener.onClickItemCategory(category));
    }
    @Override
    public int getItemCount() {
        return null == mListCategories ? 0 : mListCategories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private final ItemCategoryBinding mItemCategoryBinding;

        public CategoryViewHolder(ItemCategoryBinding itemCategoryBinding) {
            super(itemCategoryBinding.getRoot());
            this.mItemCategoryBinding = itemCategoryBinding;
        }
    }
}
