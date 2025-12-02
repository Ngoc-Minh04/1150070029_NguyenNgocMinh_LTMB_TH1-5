package com.example.ltmb_th3_lap7;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

public class Bai2Activity extends AppCompatActivity {

    Button btnToast, btnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        btnToast = findViewById(R.id.btnToast);
        btnDialog = findViewById(R.id.btnDialog);

        btnToast.setOnClickListener(v -> showCustomToast());
        btnDialog.setOnClickListener(v -> showCustomDialog());
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

    }

    private void showCustomToast() {
        View layout = LayoutInflater.from(this).inflate(R.layout.custom_toast, null);

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);

        Button btnClose = dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> dialog.dismiss());

        dialog.show();

    }
}