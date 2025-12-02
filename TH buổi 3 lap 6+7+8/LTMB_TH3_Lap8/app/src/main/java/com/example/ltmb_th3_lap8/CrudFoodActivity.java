package com.example.ltmb_th3_lap8;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class CrudFoodActivity extends AppCompatActivity {

    ArrayList<Product> list = new ArrayList<>();
    ListView lv;
    MyListViewAdapter adapter;
    int selected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_food);

        lv = findViewById(R.id.lvFood);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());


        list.add(new Product(R.drawable.pho, "Phở", "Đậm vị thơm ngon"));
        list.add(new Product(R.drawable.banhmi, "Bánh mì", "Nóng giòn"));
        list.add(new Product(R.drawable.com, "Cơm sườn", "Đậm đà ngon"));

        adapter = new MyListViewAdapter(list);
        lv.setAdapter(adapter);


        registerForContextMenu(lv);

        lv.setOnItemLongClickListener((adapterView, view, position, id) -> {
            selected = position;
            return false;
        });


        btnAdd.setOnClickListener(v -> showAddDialog());
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 1, 0, "Sửa");
        menu.add(0, 2, 0, "Xóa");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            showEditDialog();
        } else if (item.getItemId() == 2) {
            list.remove(selected);
            adapter.notifyDataSetChanged();
        }
        return true;
    }

    private void showAddDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_food, null);
        EditText edtTitle = view.findViewById(R.id.edtTitle);
        EditText edtContent = view.findViewById(R.id.edtContent);

        new AlertDialog.Builder(this)
                .setTitle("Thêm món")
                .setView(view)
                .setPositiveButton("Thêm", (dialog, which) -> {
                    list.add(new Product(
                            R.drawable.pho,
                            edtTitle.getText().toString(),
                            edtContent.getText().toString()));
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void showEditDialog() {
        Product p = list.get(selected);

        View view = getLayoutInflater().inflate(R.layout.dialog_food, null);
        EditText edtTitle = view.findViewById(R.id.edtTitle);
        EditText edtContent = view.findViewById(R.id.edtContent);

        edtTitle.setText(p.title);
        edtContent.setText(p.content);

        new AlertDialog.Builder(this)
                .setTitle("Sửa món")
                .setView(view)
                .setPositiveButton("Lưu", (dialog, which) -> {
                    p.title = edtTitle.getText().toString();
                    p.content = edtContent.getText().toString();
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }
}
