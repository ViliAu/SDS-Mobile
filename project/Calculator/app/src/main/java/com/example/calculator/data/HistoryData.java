package com.example.calculator.data;

import java.util.ArrayList;

public class HistoryData {

    public static final HistoryData instance = new HistoryData();

    private HistoryData() {}

    private ArrayList<String> historyList = new ArrayList<>();

    public void appendList(String formula) {
        historyList.add(formula);
    }

    public ArrayList<String> getList() {
        return historyList;
    }
}
