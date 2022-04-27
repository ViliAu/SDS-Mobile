package com.ukko.module3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    String[] items, prices, descriptions;

    public ItemAdapter(Context c, String[] i, String[] p, String[] d) {
        items = i;
        prices = p;
        descriptions = d;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_listview_detail, viewGroup, false);
        TextView tvName = v.findViewById(R.id.tv_name);
        TextView tvPrice = v.findViewById(R.id.tv_price);
        TextView tvDesc = v.findViewById(R.id.tv_desc);

        tvName.setText(items[i]);
        tvPrice.setText(prices[i]);
        tvDesc.setText(descriptions[i]);
        return v;
    }
}
