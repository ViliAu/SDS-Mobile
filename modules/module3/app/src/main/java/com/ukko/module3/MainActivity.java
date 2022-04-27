package com.ukko.module3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ukko.module3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String[] items, prices, descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListView();
    }

    private void setupListView() {
        Resources res = getResources();

        items = res.getStringArray(R.array.items);
        prices = res.getStringArray(R.array.prices);
        descriptions = res.getStringArray(R.array.descriptions);

        ItemAdapter ia = new ItemAdapter(this, items, prices, descriptions);
        binding.lvMain.setAdapter(ia);

        binding.lvMain.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent showDetail = new Intent(this, DetailActivity.class);
            showDetail.putExtra("index", i);
            startActivity(showDetail);
        });
    }
}