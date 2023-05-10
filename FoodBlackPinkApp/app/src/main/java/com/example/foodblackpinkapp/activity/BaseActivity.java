package com.example.foodblackpinkapp.activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import com.example.foodblackpinkapp.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public abstract class BaseActivity extends AppCompatActivity {
    protected AlertDialog progressDialog, alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createProgressDialog();
        createAlertDialog();
        hideTitleBar();
    }

    public void createProgressDialog() {
        progressDialog = new MaterialAlertDialogBuilder(this)
                .setMessage(R.string.waiting_message)
                .setCancelable(false)
                .setView(R.layout.progress_dialog)
                .create();
    }

    public void showProgressDialog(boolean value) {
        if (value) {
            if (progressDialog != null && !progressDialog.isShowing()) {
                progressDialog.show();
            }
        } else {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    public void createAlertDialog() {
        alertDialog = new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.app_name)
                .setPositiveButton(R.string.action_ok, (dialog, which) -> dialog.dismiss())
                .setCancelable(false)
                .create();
    }

    public void showAlertDialog(String errorMessage) {
        alertDialog.setMessage(errorMessage);
        alertDialog.show();
    }

    public void showAlertDialog(@StringRes int resourceId) {
        alertDialog.setMessage(getString(resourceId));
        alertDialog.show();
    }

    public void setCancelProgress(boolean isCancel) {
        if (progressDialog != null) {
            progressDialog.setCancelable(isCancel);
        }
    }

    @Override
    protected void onDestroy() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        super.onDestroy();
    }
    protected void hideTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
