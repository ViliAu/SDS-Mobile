package com.example.calculator.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.calculator.data.HistoryData;
import com.example.calculator.databinding.FragmentCalculatorBinding;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.Locale;

public class CalculatorFragment extends Fragment {

    private FragmentCalculatorBinding binding;

    private String mathString = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        initSymbolButtons();
        initNumberButtons();
        initOther();
        mathString = HistoryData.instance.getPlaceHolderFormula();
        binding.tvFormula.setText(mathString);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initSymbolButtons() {
        binding.btnPlus.setOnClickListener(v -> {
            appendMathString("+");
        });
        binding.btnMinus.setOnClickListener(v -> {
            appendMathString("-");
        });
        binding.btnMultiply.setOnClickListener(v -> {
            appendMathString("*");
        });
        binding.btnPower.setOnClickListener(v -> {
            appendMathString("^(");
        });
        binding.btnDivide.setOnClickListener(v -> {
            appendMathString("/");
        });
    }

    private void initNumberButtons() {
        binding.btn0.setOnClickListener(v -> {
            appendMathString("0");
        });
        binding.btn1.setOnClickListener(v -> {
            appendMathString("1");
        });
        binding.btn2.setOnClickListener(v -> {
            appendMathString("2");
        });
        binding.btn3.setOnClickListener(v -> {
            appendMathString("3");
        });
        binding.btn4.setOnClickListener(v -> {
            appendMathString("4");
        });
        binding.btn5.setOnClickListener(v -> {
            appendMathString("5");
        });
        binding.btn6.setOnClickListener(v -> {
            appendMathString("6");
        });
        binding.btn7.setOnClickListener(v -> {
            appendMathString("7");
        });
        binding.btn8.setOnClickListener(v -> {
            appendMathString("8");
        });
        binding.btn9.setOnClickListener(v -> {
            appendMathString("9");
        });
        binding.btnPeriod.setOnClickListener(v -> {
            appendMathString(".");
        });
    }

    private void initOther() {
        binding.btnParentheses.setOnClickListener(v -> {
            if (!mathString.contains("(") || mathString.charAt(mathString.length()-1) == '(') {
                appendMathString("(");
            }
            else {
                appendMathString(")");
            }
        });

        binding.btnEquals.setOnClickListener(v -> {
            calculateMathString();
        });

        binding.btnErase.setOnClickListener(v -> {
            erase();
        });

        binding.btnCe.setOnClickListener(v-> {
            clearMathString();
        });
    }

    private void appendMathString(String c) {
        if (mathString.equals("NaN") || mathString.equals("0")) {
            mathString = "";
        }
        mathString += c;
        binding.tvFormula.setText(mathString);
    }

    private void erase() {
        if (mathString.equals("NaN")) {
            mathString = "";
        }
        if (mathString.length() > 0)
            mathString = mathString.substring(0, mathString.length()-1);
            binding.tvFormula.setText(mathString);
    }

    private void clearMathString() {
        mathString = "";
        binding.tvFormula.setText(mathString);
    }

    private void evaluateMathString() {
        if (mathString.length() > 0 && !mathString.matches(".*([0-9]|\\))")) {
            erase();
            evaluateMathString();
        }
        int diff = calculateParenthesesDifference();
        for (int i = 0; i < diff; i++) {
            mathString += ')';
        }
    }

    private void calculateMathString() {
        evaluateMathString();
        if (mathString.length() == 0) {
            appendMathString("0");
            return;
        }
        Expression ex = new Expression(mathString);

        double sum = ex.calculate();
        mathString = String.format(Locale.GERMANY, "%.2f", sum);
        mathString = mathString.replace(',', '.');
        binding.tvFormula.setText(mathString);

        // Append history
        HistoryData.instance.appendList(ex.getExpressionString());
        HistoryData.instance.appendList(mathString);
    }

    private int calculateParenthesesDifference() {
        int startAmount = 0;
        int endAmount = 0;
        for (char c : mathString.toCharArray()) {
            if (c == '(') {
                startAmount++;
            }
            else if (c == ')') {
                endAmount++;
            }
        }
        return startAmount - endAmount;
    }
}
