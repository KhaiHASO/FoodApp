package com.example.fooddr.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddr.Activites.ProductDetailActivity;
import com.example.fooddr.Models.LastProduct;
import com.example.fooddr.R;

import java.util.List;

public class LastProductAdapter extends RecyclerView.Adapter<LastProductAdapter.LastProductViewHolder>{
    private static final String TAG = "LastProductAdapter";
    private List<LastProduct> mLastProduct;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public LastProductAdapter(Context context, List<LastProduct> lastProducts) {
        mContext = context;
        mLastProduct = lastProducts;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LastProductAdapter.LastProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_popular, parent, false);
        return new LastProductAdapter.LastProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LastProductAdapter.LastProductViewHolder holder, int position) {
        String imagePath = "";
        int drawableResourceId;
        LastProduct lastProduct = mLastProduct.get(position);
        Glide.with(holder.itemView.getContext())
                .load(lastProduct.getImageLastPD())
                .into(holder.lastProductImage);
        holder.lastProductLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickGoToShowDetail(lastProduct);
            }
        });

    }
    private void OnClickGoToShowDetail(LastProduct lastProduct){
        Intent intent = new Intent(mContext, ProductDetailActivity.class);
        intent.putExtra("id",String.valueOf(lastProduct.getIdMealPD()));
        mContext.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return mLastProduct.size();
    }

    public class LastProductViewHolder extends RecyclerView.ViewHolder {
        ImageView lastProductImage;
        ConstraintLayout lastProductLayout;
        public LastProductViewHolder(@NonNull View itemView) {
            super(itemView);
            lastProductImage = itemView.findViewById(R.id.foodImage);
            lastProductLayout = itemView.findViewById(R.id.foodLayout);
        }
    }
}