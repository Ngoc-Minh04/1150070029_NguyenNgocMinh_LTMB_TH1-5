package com.example.a1150070029_nguyenngocminh_ltmb_th3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class LengthActivity extends AppCompatActivity {

    EditText txtValue;
    Spinner spFromLength, spToLength;
    Button btnLengthConvert, btnBack;
    TextView txtLengthResult;

    String[] units = {"mm", "cm", "dm", "m", "dam", "hm", "km"};

    double[][] rate = {
            {1, 0.1, 0.01, 0.001, 0.0001, 0.00001, 0.000001},
            {10, 1, 0.1, 0.01, 0.001, 0.0001, 0.00001},
            {100, 10, 1, 0.1, 0.01, 0.001, 0.0001},
            {1000, 100, 10, 1, 0.1, 0.01, 0.001},
            {10000, 1000, 100, 10, 1, 0.1, 0.01},
            {100000, 10000, 1000, 100, 10, 1, 0.1},
            {1000000, 100000, 10000, 1000, 100, 10, 1}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        txtValue = findViewById(R.id.txtValue);
        spFromLength = findViewById(R.id.spFromLength);
        spToLength = findViewById(R.id.spToLength);
        btnLengthConvert = findViewById(R.id.btnLengthConvert);
        txtLengthResult = findViewById(R.id.txtLengthResult);

        // nút Back
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spFromLength.setAdapter(adapter);
        spToLength.setAdapter(adapter);

        btnLengthConvert.setOnClickListener(v -> {
            try {
                double value = Double.parseDouble(txtValue.getText().toString());
                int from = spFromLength.getSelectedItemPosition();
                int to = spToLength.getSelectedItemPosition();

                double result = value * rate[from][to];
                txtLengthResult.setText("Kết quả: " + result);

            } catch (Exception e) {
                txtLengthResult.setText("Vui lòng nhập giá trị hợp lệ!");
            }
        });
    }
}
