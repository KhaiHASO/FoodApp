package com.example.foodblackpinkapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodblackpinkapp.constant.Constant;
import com.example.foodblackpinkapp.databinding.ItemOrderBinding;
import com.example.foodblackpinkapp.model.BillViewDTO;


import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private final List<BillViewDTO> mListOrder;

    public OrderAdapter(List<BillViewDTO> mListOrder) {
        this.mListOrder = mListOrder;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderBinding itemOrderBinding = ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new OrderViewHolder(itemOrderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {


        BillViewDTO bill = mListOrder.get(position);
        if (bill == null) {
            return;
        }
        holder.mItemOrderBinding.tvId.setText(String.valueOf(bill.getOrderId()));
        holder.mItemOrderBinding.tvName.setText(bill.getFullName());
        holder.mItemOrderBinding.tvPhone.setText(bill.getPhone());
        holder.mItemOrderBinding.tvAddress.setText(bill.getAddress());
        holder.mItemOrderBinding.tvMenu.setText(bill.getProductList());
        holder.mItemOrderBinding.tvDate.setText(bill.getOderDate());

        //String strAmount = String.valueOf(bill.getTotalPrice())+".00 VND";
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        String strAmount = numberFormat.format(bill.getTotalPrice()) + ",000VND";
        holder.mItemOrderBinding.tvTotalAmount.setText(strAmount);

        String paymentMethod = "";
        paymentMethod = Constant.PAYMENT_METHOD_CASH;
        holder.mItemOrderBinding.tvPayment.setText(paymentMethod);
    }

    @Override
    public int getItemCount() {
        if (mListOrder != null) {
            return mListOrder.size();
        }
        return 0;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        private final ItemOrderBinding mItemOrderBinding;

        public OrderViewHolder(@NonNull ItemOrderBinding itemOrderBinding) {
            super(itemOrderBinding.getRoot());
            this.mItemOrderBinding = itemOrderBinding;
        }
    }
}
