package com.example.ltmb_th3_lap8;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

public class MyGridAdapter extends BaseAdapter {
    ArrayList<Product> list;

    public MyGridAdapter(ArrayList<Product> list) {
        this.list = list;
    }

    public int getCount() { return list.size(); }
    public Object getItem(int i) { return list.get(i); }
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {

        view = View.inflate(parent.getContext(), R.layout.row, null);

        Product p = list.get(pos);

        ImageView img = view.findViewById(R.id.imgRow);
        TextView t = view.findViewById(R.id.txtTitle);
        TextView c = view.findViewById(R.id.txtContent);

        img.setImageResource(p.image);
        t.setText(p.title);
        c.setText(p.content);

        return view;
    }
}
