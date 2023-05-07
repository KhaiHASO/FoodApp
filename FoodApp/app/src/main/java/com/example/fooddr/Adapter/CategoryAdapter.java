package com.example.fooddr.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddr.Activites.CategoryActivity;
import com.example.fooddr.Models.Category;
import com.example.fooddr.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private static final String TAG = "CategoryAdapter";
    private List<Category> listCategory;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CategoryAdapter(Context context, List<Category> categories) {
        mContext = context;
        listCategory = categories;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        String imagePath = "";
        int drawableResourceId;
        Category category = listCategory.get(position);
        holder.categoryName.setText(category.getTitle());

        Glide.with(holder.itemView.getContext())
                .load(category.getImage())
                .into(holder.categoryImage);
        holder.categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickGoToShowCate(category);
                Toast.makeText(view.getContext(), "load product", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void OnClickGoToShowCate(Category category){
        Intent intent = new Intent(mContext, CategoryActivity.class);
        intent.putExtra("idcategory",Integer.parseInt(category.getId()));
        mContext.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryImage;
        ConstraintLayout categoryLayout;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryLayout = itemView.findViewById(R.id.categoryLayout);
        }
    }
}
