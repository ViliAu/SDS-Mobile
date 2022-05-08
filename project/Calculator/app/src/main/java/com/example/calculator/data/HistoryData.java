package com.example.calculator.data;

import java.util.ArrayList;

public class HistoryData {

    public static final HistoryData instance = new HistoryData();

    private HistoryData() {}

    private ArrayList<String> historyList = new ArrayList<>();
    private String placeHolderFormula = "0";

    public void appendList(String formula) {
        this.historyList.add(formula);
    }

    public ArrayList<String> getList() {
        return this.historyList;
    }

    public String getPlaceHolderFormula() {
        String f = placeHolderFormula;
        placeHolderFormula = "0";
        return f;
    }

    public void setPlaceHolderFormula(String placeHolderFormula) {
        this.placeHolderFormula = placeHolderFormula;
    }
}
