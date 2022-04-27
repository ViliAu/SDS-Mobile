package com.ukko.module1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ukko.module1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setupButton();
    }

    private void setupButton() {
        binding.btnAdd.setOnClickListener(v -> {
            try {
                int i = Integer.parseInt(binding.etUpper.getText().toString());
                int j = Integer.parseInt(binding.etLower.getText().toString());
                binding.tvResult.setText(String.valueOf(i+j));
            }
            catch (Exception e ) {
                binding.tvResult.setText("An error has occurred!");
            }
        });
    }
}