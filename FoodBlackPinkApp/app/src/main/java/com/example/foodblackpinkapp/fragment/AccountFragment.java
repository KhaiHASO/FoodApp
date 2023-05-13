package com.example.foodblackpinkapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodblackpinkapp.R;
import com.example.foodblackpinkapp.model.Customer;
import com.example.foodblackpinkapp.sharereferrences.ShareRefManager;
import com.example.foodblackpinkapp.utils.GlideUtils;
import com.example.foodblackpinkapp.activity.LoginActivity;


public class AccountFragment extends Fragment {

    private TextView customerId, email, phone, name;
    private Button btnLogout;
    private ImageView imageViewprofile;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        customerId = view.findViewById(R.id.textViewId);
        name = view.findViewById(R.id.textViewName);
        email = view.findViewById(R.id.textViewEmail);
        phone = view.findViewById(R.id.textViewPhone);
        btnLogout = view.findViewById(R.id.btnLogOut);
        imageViewprofile = view.findViewById(R.id.imageViewProfile);

        Customer customer = ShareRefManager.getInstance(getActivity()).getCustomer();
        customerId.setText(String.valueOf(customer.getCustomerId()));
        name.setText(customer.getFullname());
        email.setText(customer.getEmail());
        phone.setText(customer.getPhone());
        Glide.with(getActivity())
                .load(customer.getPhoto())
                .into(imageViewprofile);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareRefManager.getInstance(getActivity()).logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}