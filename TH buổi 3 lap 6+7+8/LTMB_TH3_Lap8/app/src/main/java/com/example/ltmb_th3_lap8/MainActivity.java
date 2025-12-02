package com.example.ltmb_th3_lap8;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnBasic, btnCustomList, btnGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBasic = findViewById(R.id.btnBasic);
        btnCustomList = findViewById(R.id.btnCustomList);
        btnGrid = findViewById(R.id.btnGrid);

        btnBasic.setOnClickListener(v ->
                startActivity(new Intent(this, BasicListActivity.class)));

        btnCustomList.setOnClickListener(v ->
                startActivity(new Intent(this, CustomListActivity.class)));

        btnGrid.setOnClickListener(v ->
                startActivity(new Intent(this, CustomGridActivity.class)));
        Button btnCrud = findViewById(R.id.btnCrud);
        btnCrud.setOnClickListener(v ->
                startActivity(new Intent(this, CrudFoodActivity.class)));

    }
}
