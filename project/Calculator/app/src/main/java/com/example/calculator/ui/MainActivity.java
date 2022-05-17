/*
    Author: Vili Huusko
    Last modified: 05.05.2022
    Source(s):
        Formula split regex: https://stackoverflow.com/questions/27808112/java-splitting-with-math-expression
*/

package com.example.calculator.ui;

import android.app.UiModeManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.calculator.R;
import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        initBottomNavigationView();
        changeFragment(CalculatorFragment.class);
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
    }

    private void changeFragment(Class<? extends Fragment> f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(binding.fragmentContainer.getId(), f, null);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    // Called from history fragment
    public void updateNav(Class<? extends Fragment> f) {
        if (f == CalculatorFragment.class)
            binding.bottomNavigationView.setSelectedItemId(R.id.calculate);
        else
            binding.bottomNavigationView.setSelectedItemId(R.id.history);
    }
}