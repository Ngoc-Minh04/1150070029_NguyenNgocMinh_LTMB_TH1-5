package com.example.lap4_ltmb;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;

import android.telephony.SmsMessage;

public class MainActivity extends AppCompatActivity {

    private TextView tvContent;

    // Runtime permission launcher cho RECEIVE_SMS
    private final ActivityResultLauncher<String> smsPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (!isGranted) {
                    Toast.makeText(this, "Bạn cần cấp quyền RECEIVE_SMS để nhận tin nhắn.", Toast.LENGTH_LONG).show();
                }
            });

    // BroadcastReceiver đăng ký BẰNG CODE
    private final BroadcastReceiver smsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
                // Lấy PDUs và parse tin nhắn
                Object[] pdus = (Object[]) intent.getExtras().get("pdus");
                String format = intent.getStringExtra("format"); // cần cho API >= 23

                if (pdus != null) {
                    StringBuilder fullMsg = new StringBuilder();
                    String from = "";

                    for (Object pdu : pdus) {
                        SmsMessage sms;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            sms = SmsMessage.createFromPdu((byte[]) pdu, format);
                        } else {
                            sms = SmsMessage.createFromPdu((byte[]) pdu);
                        }
                        if (from.isEmpty()) {
                            from = sms.getDisplayOriginatingAddress();
                        }
                        fullMsg.append(sms.getMessageBody());
                    }

                    String display = "From: " + from + "\nContent: " + fullMsg;
                    tvContent.setText(display);
                    Toast.makeText(context, "Có tin nhắn mới!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Trỏ đúng layout có tv_content để hết lỗi Cannot resolve symbol
        setContentView(R.layout.activity_main);

        tvContent = findViewById(R.id.tv_content);

        // Xin quyền RECEIVE_SMS nếu chưa được cấp
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            smsPermissionLauncher.launch(Manifest.permission.RECEIVE_SMS);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Đăng ký lắng nghe SMS khi Activity hiển thị
        IntentFilter filter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        // Ưu tiên cao hơn một chút (không bắt buộc)
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        registerReceiver(smsReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Hủy đăng ký khi Activity dừng -> đúng với yêu cầu "đăng ký bằng code" nên app tắt sẽ không nghe nữa
        try {
            unregisterReceiver(smsReceiver);
        } catch (IllegalArgumentException ignored) { /* đã unregister rồi */ }
    }
}