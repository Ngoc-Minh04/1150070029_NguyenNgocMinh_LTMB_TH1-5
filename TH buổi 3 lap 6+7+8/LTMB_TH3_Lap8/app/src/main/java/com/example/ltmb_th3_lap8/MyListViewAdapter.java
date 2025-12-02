package com.example.ltmb_th3_lap8;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

public class MyListViewAdapter extends BaseAdapter {

    ArrayList<Product> list;

    public MyListViewAdapter(ArrayList<Product> list) {
        this.list = list;
    }

    @Override
    public int getCount() { return list.size(); }

    @Override
    public Object getItem(int position) { return list.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        View view = View.inflate(parent.getContext(), R.layout.row, null);

        Product p = list.get(pos);

        ImageView img = view.findViewById(R.id.imgRow);
        TextView t1 = view.findViewById(R.id.txtTitle);
        TextView t2 = view.findViewById(R.id.txtContent);

        img.setImageResource(p.image);
        t1.setText(p.title);
        t2.setText(p.content);

        return view;
    }
}
