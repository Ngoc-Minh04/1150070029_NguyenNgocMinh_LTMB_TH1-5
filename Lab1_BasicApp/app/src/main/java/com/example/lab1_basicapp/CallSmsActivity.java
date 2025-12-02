package com.example.lab1_basicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CallSmsActivity extends AppCompatActivity {

    private EditText txtPhone, txtMessage;
    private Button btnCall, btnSms, btnBack;
    private ImageView imgCallIcon;
    private static final int REQUEST_CALL_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_sms);

        // Ánh xạ các view
        txtPhone = findViewById(R.id.txtPhone);
        txtMessage = findViewById(R.id.txtMessage);
        btnCall = findViewById(R.id.btnCall);
        btnSms = findViewById(R.id.btnSms);
        btnBack = findViewById(R.id.btnBack);
        imgCallIcon = findViewById(R.id.imgCallIcon);

        // 📞 Gọi điện bằng nút
        btnCall.setOnClickListener(v -> makePhoneCall());

        // ✉️ Gửi tin nhắn
        btnSms.setOnClickListener(v -> {
            String phoneNumber = txtPhone.getText().toString().trim();
            String message = txtMessage.getText().toString().trim();

            if (phoneNumber.isEmpty() || message.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập số điện thoại và nội dung!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("smsto:" + phoneNumber));
            smsIntent.putExtra("sms_body", message);
            startActivity(smsIntent);
        });

        // ⬅️ Quay lại màn hình chính
        btnBack.setOnClickListener(v -> finish());

        // ☎️ Icon gọi nhanh (ACTION_DIAL)
        imgCallIcon.setOnClickListener(v -> {
            String phoneNumber = txtPhone.getText().toString().trim();
            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Nhập số điện thoại trước!", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        });
    }

    // Hàm gọi điện có kiểm tra quyền
    private void makePhoneCall() {
        String phoneNumber = txtPhone.getText().toString().trim();

        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số điện thoại!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PERMISSION);
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        }
    }

    // Xử lý khi người dùng cấp quyền
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Bạn chưa cấp quyền gọi điện!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
