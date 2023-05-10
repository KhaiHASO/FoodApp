package com.example.fooddr.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddr.Activites.ProductDetailActivity;
import com.example.fooddr.Models.Product;
import com.example.fooddr.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MealViewHolder>{
    private static final String TAG = "ProductAdapter";
    private List<Product> mProduct;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ProductAdapter(Context context, List<Product> product) {
        mContext = context;
        mProduct = product;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProductAdapter.MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.activity_product, parent, false);
        return new ProductAdapter.MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MealViewHolder holder, int position) {
        String imagePath = "";
        int drawableResourceId;
        Product product = mProduct.get(position);
        holder.mealName.setText(product.getNameMeal());
        Glide.with(holder.itemView.getContext())
                .load(product.getNameMealThumb())
                .into(holder.mealImage);
        holder.mealLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickGoToShowDetail(product);
            }
        });

    }
    private void OnClickGoToShowDetail(Product product){
        Intent intent = new Intent(mContext, ProductDetailActivity.class);
        intent.putExtra("id",String.valueOf(product.getIdMeal()));
        mContext.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return mProduct.size();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {
        TextView mealName;
        ImageView mealImage;
        ConstraintLayout mealLayout;
        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.mealName);
            mealImage = itemView.findViewById(R.id.mealImage);
            mealLayout = itemView.findViewById(R.id.mealLayout);
        }
    }
}
