package com.example.ltmb_th3_lap8;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        ListView lv = findViewById(R.id.lvCustom);

        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product(R.drawable.pho, "Phở", "Đậm vị thơm ngon"));
        list.add(new Product(R.drawable.banhmi, "Bánh mì", "Nóng giòn"));
        list.add(new Product(R.drawable.com, "Cơm sườn", "Dẻo thơm ngon"));

        MyListViewAdapter adapter = new MyListViewAdapter(list);
        lv.setAdapter(adapter);
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());





    }
}

