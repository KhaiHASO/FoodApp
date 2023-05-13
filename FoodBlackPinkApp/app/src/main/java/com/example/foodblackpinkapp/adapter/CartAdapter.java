package com.example.foodblackpinkapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodblackpinkapp.constant.Constant;
import com.example.foodblackpinkapp.databinding.ItemCartBinding;
import com.example.foodblackpinkapp.model.Product;
import com.example.foodblackpinkapp.utils.GlideUtils;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<Product> mListProducts;
    private final IClickListener iClickListener;

    public interface IClickListener {
        void clickDeleteProduct(Product product, int position);

        void updateItemProduct(Product product, int position);
    }

    public CartAdapter(List<Product> mListProducts, IClickListener iClickListener) {
        this.mListProducts = mListProducts;
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
        Product product = mListProducts.get(position);
        if (product == null) {
            return;
        }
        GlideUtils.loadUrl(product.getImage(), holder.mItemCartBinding.imgFoodCart);
        holder.mItemCartBinding.tvFoodNameCart.setText(product.getName());

        String strProductPriceCart = product.getPrice() + Constant.CURRENCY;
        if (product.getDiscount() > 0) {
            strProductPriceCart = product.getRealPrice() + Constant.CURRENCY;
        }
        holder.mItemCartBinding.tvFoodPriceCart.setText(strProductPriceCart);

        holder.mItemCartBinding.tvSubtract.setOnClickListener(v -> {
            int count = product.getQuantity();
            if (count <= 1) {
                return;
            }
            int newCount = count - 1;
            product.setQuantity(newCount);

            // Gọi phương thức xử lý sự kiện updateItemProduct trong IClickListener
            iClickListener.updateItemProduct(product, holder.getAdapterPosition());
        });

        holder.mItemCartBinding.tvAdd.setOnClickListener(v -> {
            int newCount = product.getQuantity() + 1;
            product.setQuantity(newCount);

            // Gọi phương thức xử lý sự kiện updateItemProduct trong IClickListener
            iClickListener.updateItemProduct(product, holder.getAdapterPosition());
        });

        holder.mItemCartBinding.tvDelete.setOnClickListener(v -> {
            // Gọi phương thức xử lý sự kiện clickDeleteProduct trong IClickListener
            iClickListener.clickDeleteProduct(product, holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return null == mListProducts ? 0 : mListProducts.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        private final ItemCartBinding mItemCartBinding;

        public CartViewHolder(ItemCartBinding itemCartBinding) {
            super(itemCartBinding.getRoot());
            this.mItemCartBinding = itemCartBinding;
        }
    }
}
