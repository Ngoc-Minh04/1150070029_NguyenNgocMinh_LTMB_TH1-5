package com.example.ltmb_th3_lap8;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.util.ArrayList;

public class CustomGridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_grid);

        GridView gv = findViewById(R.id.gridView);

        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product(R.drawable.pho, "Phở", "đậm vị thơm ngon"));
        list.add(new Product(R.drawable.banhmi, "Bánh mì", "nóng giòn "));
        list.add(new Product(R.drawable.com, "Cơm sườn", "dẻo ngon"));

        gv.setAdapter(new MyGridAdapter(list));
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

    }
}