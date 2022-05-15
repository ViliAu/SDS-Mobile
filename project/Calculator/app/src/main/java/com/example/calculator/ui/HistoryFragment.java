package com.example.calculator.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.calculator.data.HistoryData;
import com.example.calculator.databinding.FragmentHistoryBinding;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        initListView();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initListView() {
        System.out.println(HistoryData.instance.getList().size());
        ArrayAdapter<String> historyAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1,
                HistoryData.instance.getList());
        binding.lvHistory.setAdapter(historyAdapter);
        binding.lvHistory.setOnItemClickListener((adapterView, view, i, l) -> {
            String f = HistoryData.instance.getList().get(i);
            HistoryData.instance.setPlaceHolderFormula(f);

            MainActivity m = (MainActivity) getActivity();
            m.changeFragment(CalculatorFragment.class);
        });
        binding.lvHistory.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, HistoryData.instance.getList()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.rgb(244, 244, 244));

                return textView;
            }
        });
    }

}
