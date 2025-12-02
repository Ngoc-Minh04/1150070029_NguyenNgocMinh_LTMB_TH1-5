package com.example.ltmb_th3_lap8;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class BasicListActivity extends AppCompatActivity {

    String[] arr = {"Phở", "Bánh mì", "Cơm sườn"};
    ListView lvBasic;
    TextView txtBasic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_list);

        lvBasic = findViewById(R.id.lvBasic);
        txtBasic = findViewById(R.id.txtBasic);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);

        lvBasic.setAdapter(adapter);

        lvBasic.setOnItemClickListener((parent, view, pos, id) ->
                txtBasic.setText(arr[pos]));
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

    }
}