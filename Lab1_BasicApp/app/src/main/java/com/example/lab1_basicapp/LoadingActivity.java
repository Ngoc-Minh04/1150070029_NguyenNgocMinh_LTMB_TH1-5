package com.example.lab1_basicapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // Giả lập tiến trình loading trong 3 giây
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            finish(); // đóng lại sau khi hoàn tất
        }, 3000);
    }
}
