package com.ukko.module2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.ukko.module2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setupButtons();
    }

    private void setupButtons() {
        binding.btnSecondActivity.setOnClickListener(v -> {
            Intent i = new Intent(this, SecondActivity.class);
            i.putExtra("text", "HELLO WORLD");
            startActivity(i);
        });

        binding.btnGoogle.setOnClickListener(v -> {
            String google = "https://www.google.com";
            Uri address = Uri.parse(google);

            Intent googleIntent = new Intent(Intent.ACTION_VIEW, address);
            if (googleIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(googleIntent);
            }
        });
    }
}