package com.example.foodblackpinkapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodblackpinkapp.R;
import com.example.foodblackpinkapp.adapter.MoreImageAdapter;
import com.example.foodblackpinkapp.constant.Constant;
import com.example.foodblackpinkapp.constant.GlobalFunction;
import com.example.foodblackpinkapp.database.ApiService;
import com.example.foodblackpinkapp.database.RetrofitBase;
import com.example.foodblackpinkapp.databinding.ActivityFoodDetailBinding;
import com.example.foodblackpinkapp.model.Product;
import com.example.foodblackpinkapp.model.ProductDetail;
import com.example.foodblackpinkapp.utils.GlideUtils;
import com.example.foodblackpinkapp.utils.StringUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodDetailActivity extends BaseActivity {

    private ActivityFoodDetailBinding mActivityFoodDetailBinding;
    private Product mProduct;
    private List<ProductDetail> mListProductDetail;
    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();
        mActivityFoodDetailBinding = ActivityFoodDetailBinding.inflate(getLayoutInflater());
        setContentView(mActivityFoodDetailBinding.getRoot());

        getDataIntent();
        initToolbar();
        setDataFoodDetail();
        initListener();
    }

    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mProduct = (Product) bundle.get(Constant.KEY_INTENT_FOOD_OBJECT);
        }
    }

    private void initToolbar() {
        mActivityFoodDetailBinding.toolbar.imgBack.setVisibility(View.VISIBLE);
        mActivityFoodDetailBinding.toolbar.imgCart.setVisibility(View.VISIBLE);
        mActivityFoodDetailBinding.toolbar.tvTitle.setText(getString(R.string.food_detail_title));

        mActivityFoodDetailBinding.toolbar.imgBack.setOnClickListener(v -> onBackPressed());
    }

    private void setDataFoodDetail() {
        if (mProduct == null) {
            return;
        }

        GlideUtils.loadUrlBanner(mProduct.getImage(), mActivityFoodDetailBinding.imageFood);
        if (mProduct.getDiscount() <= 0) {
            mActivityFoodDetailBinding.tvSaleOff.setVisibility(View.GONE);
            mActivityFoodDetailBinding.tvPrice.setVisibility(View.GONE);

            String strPrice = mProduct.getPrice() + Constant.CURRENCY;
            mActivityFoodDetailBinding.tvPriceSale.setText(strPrice);
        } else {
            mActivityFoodDetailBinding.tvSaleOff.setVisibility(View.VISIBLE);
            mActivityFoodDetailBinding.tvPrice.setVisibility(View.VISIBLE);

            String strSale = "Giảm " + mProduct.getDiscount() + "%";
            mActivityFoodDetailBinding.tvSaleOff.setText(strSale);

            String strPriceOld = mProduct.getPrice() + Constant.CURRENCY;
            mActivityFoodDetailBinding.tvPrice.setText(strPriceOld);
            mActivityFoodDetailBinding.tvPrice.setPaintFlags(mActivityFoodDetailBinding.tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            String strRealPrice = mProduct.getRealPrice() + Constant.CURRENCY;
            mActivityFoodDetailBinding.tvPriceSale.setText(strRealPrice);
        }
        mActivityFoodDetailBinding.tvFoodName.setText(mProduct.getName());
        mActivityFoodDetailBinding.tvFoodDescription.setText(mProduct.getDescription());

        displayListMoreImages();

        setStatusButtonAddToCart();
    }

    private void displayListMoreImages() {
        mActivityFoodDetailBinding.tvMoreImageLabel.setVisibility(View.VISIBLE);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mActivityFoodDetailBinding.rcvImages.setLayoutManager(gridLayoutManager);

        getListImageProduct(mProduct);
    }

    private void getListImageProduct(Product product) {
        mApiService = RetrofitBase.getInstance();
        mApiService.getProductDetailByProductId(product.getProductId()).enqueue(new Callback<List<ProductDetail>>() {
            @Override
            public void onResponse(Call<List<ProductDetail>> call, Response<List<ProductDetail>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mListProductDetail = new ArrayList<>();
                    for (ProductDetail productDetail : response.body()) {
                        mListProductDetail.add(0, productDetail);
                        Log.d("PRODUCT DETAIL", "ProductDetail: " + productDetail.toString());
                    }
                    // Tạo adapter sau khi danh sách đã được lấy về
                    MoreImageAdapter moreImageAdapter = new MoreImageAdapter(mListProductDetail);
                    mActivityFoodDetailBinding.rcvImages.setAdapter(moreImageAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ProductDetail>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setStatusButtonAddToCart() {
        if (isFoodInCart()) {
            mActivityFoodDetailBinding.tvAddToCart.setBackgroundResource(R.drawable.bg_gray_shape_corner_6);
            mActivityFoodDetailBinding.tvAddToCart.setText(getString(R.string.added_to_cart));
            mActivityFoodDetailBinding.tvAddToCart.setTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
            mActivityFoodDetailBinding.toolbar.imgCart.setVisibility(View.GONE);
        } else {
            mActivityFoodDetailBinding.tvAddToCart.setBackgroundResource(R.drawable.bg_green_shape_corner_6);
            mActivityFoodDetailBinding.tvAddToCart.setText(getString(R.string.add_to_cart));
            mActivityFoodDetailBinding.tvAddToCart.setTextColor(ContextCompat.getColor(this, R.color.white));
            mActivityFoodDetailBinding.toolbar.imgCart.setVisibility(View.VISIBLE);
        }
    }

    private boolean isFoodInCart() {
        //List<Product> list = FoodDatabase.getInstance(this).foodDAO().checkFoodInCart(mProduct.getId());
        //return list != null && !list.isEmpty();
        return false;
    }

    private void initListener() {
        mActivityFoodDetailBinding.tvAddToCart.setOnClickListener(v -> onClickAddToCart());
        mActivityFoodDetailBinding.toolbar.imgCart.setOnClickListener(v -> onClickAddToCart());
    }

    public void onClickAddToCart() {
        if (isFoodInCart()) {
            return;
        }

        @SuppressLint("InflateParams") View viewDialog = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_cart, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewDialog);

        ImageView imgFoodCart = viewDialog.findViewById(R.id.img_food_cart);
        TextView tvFoodNameCart = viewDialog.findViewById(R.id.tv_food_name_cart);
        TextView tvFoodPriceCart = viewDialog.findViewById(R.id.tv_food_price_cart);
        TextView tvSubtractCount = viewDialog.findViewById(R.id.tv_subtract);
        TextView tvCount = viewDialog.findViewById(R.id.tv_count);
        TextView tvAddCount = viewDialog.findViewById(R.id.tv_add);
        TextView tvCancel = viewDialog.findViewById(R.id.tv_cancel);
        TextView tvAddCart = viewDialog.findViewById(R.id.tv_add_cart);

        GlideUtils.loadUrl(mProduct.getImage(), imgFoodCart);
        tvFoodNameCart.setText(mProduct.getName());

        int totalPrice = mProduct.getRealPrice();
        String strTotalPrice = totalPrice + Constant.CURRENCY;
        tvFoodPriceCart.setText(strTotalPrice);

        tvSubtractCount.setOnClickListener(v -> {
            // Giảm số lượng sản phẩm trong giỏ hàng
            int count = Integer.parseInt(tvCount.getText().toString());
            if (count <= 1) {
                return;
            }
            int newCount = Integer.parseInt(tvCount.getText().toString()) - 1;
            tvCount.setText(String.valueOf(newCount));

            int totalPrice1 = mProduct.getRealPrice() * newCount;
            String strTotalPrice1 = totalPrice1 + Constant.CURRENCY;
            tvFoodPriceCart.setText(strTotalPrice1);

            //mProduct.setCount(newCount);
            //mProduct.setTotalPrice(totalPrice1);
        });

        tvAddCount.setOnClickListener(v -> {
            // Tăng số lượng sản phẩm trong giỏ hàng
            int newCount = Integer.parseInt(tvCount.getText().toString()) + 1;
            tvCount.setText(String.valueOf(newCount));

            int totalPrice2 = mProduct.getRealPrice() * newCount;
            String strTotalPrice2 = totalPrice2 + Constant.CURRENCY;
            tvFoodPriceCart.setText(strTotalPrice2);

            //mFood.setCount(newCount);
            //mFood.setTotalPrice(totalPrice2);
        });

        tvCancel.setOnClickListener(v -> bottomSheetDialog.dismiss());

        tvAddCart.setOnClickListener(v -> {
            // Thêm sản phẩm vào giỏ hàng
            bottomSheetDialog.dismiss();
            setStatusButtonAddToCart();
            // Gọi phương thức backend để thêm sản phẩm vào giỏ hàng
        });

        bottomSheetDialog.show();
    }

}