package com.example.a1150070029_nguyenngocminh_ltmb_th3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    EditText txtAmount;
    Spinner spFrom, spTo;
    Button btnConvert, btnOpenLength;
    TextView txtResult;

    // Danh sách loại tiền
    String[] units = {"USD", "EUR", "GBP", "AUD", "CAD", "ZAR", "NZD", "VND", "INR", "JPY"};

    // Ma trận tỉ giá chuẩn
    double[][] rate = {
            {1, 0.92, 0.79, 1.51, 1.36, 18.20, 1.64, 24000, 83.0, 150.0},
            {1.09, 1, 0.86, 1.64, 1.48, 19.80, 1.77, 26000, 90.0, 160.0},
            {1.26, 1.16, 1, 1.90, 1.72, 23.00, 2.05, 30000, 104, 185.0},
            {0.66, 0.61, 0.53, 1, 0.90, 12.00, 1.08, 16000, 55.0, 100.0},
            {0.73, 0.68, 0.58, 1.10, 1, 13.40, 1.20, 17000, 62.0, 110.0},
            {0.055, 0.050, 0.043, 0.083, 0.075, 1, 0.09, 1250, 4.7, 8.5},
            {0.61, 0.56, 0.49, 0.93, 0.84, 10.80, 1, 15000, 58.0, 102.0},
            {0.000042, 0.000038, 0.000033, 0.000062, 0.000058, 0.00080, 0.000067, 1, 0.0038, 0.0068},
            {0.012, 0.011, 0.0096, 0.018, 0.016, 0.21, 0.017, 260, 1, 1.75},
            {0.0067, 0.0062, 0.0054, 0.010, 0.009, 0.12, 0.0098, 147, 0.57, 1}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAmount = findViewById(R.id.txtAmount);
        spFrom = findViewById(R.id.spFrom);
        spTo = findViewById(R.id.spTo);
        btnConvert = findViewById(R.id.btnConvert);
        txtResult = findViewById(R.id.txtResult);

        btnOpenLength = findViewById(R.id.btnOpenLength);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spFrom.setAdapter(adapter);
        spTo.setAdapter(adapter);

        btnConvert.setOnClickListener(v -> {
            try {
                double amount = Double.parseDouble(txtAmount.getText().toString());
                int from = spFrom.getSelectedItemPosition();
                int to = spTo.getSelectedItemPosition();

                double result = amount * rate[from][to];
                txtResult.setText("Kết quả: " + result);

            } catch (Exception e) {
                txtResult.setText("Vui lòng nhập số hợp lệ!");
            }
        });

        // Mở màn hình đổi độ dài
        btnOpenLength.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LengthActivity.class);
            startActivity(intent);
        });
    }
}
