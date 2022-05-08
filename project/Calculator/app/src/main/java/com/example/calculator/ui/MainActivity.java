/*
    Author: Vili Huusko
    Last modified: 05.05.2022
    Source(s):
        Formula split regex: https://stackoverflow.com/questions/27808112/java-splitting-with-math-expression
*/

package com.example.calculator.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.calculator.R;
import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private String mathString = "";

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initBottomNavigationView();
    }

    private void initBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.calculate) {
                changeFragment(CalculatorFragment.class);
            }
            else {
                changeFragment(HistoryFragment.class);
            }
            return true;
        });

        // Prevent reselecting
        binding.bottomNavigationView.setOnItemReselectedListener(item -> System.out.println(item.getItemId()));
    }

    private void changeFragment(Class<? extends Fragment> f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(binding.fragmentContainer.getId(), f, null);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}