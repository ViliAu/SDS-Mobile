/*
    Author: Vili Huusko
    Last modified: 05.05.2022
    Source(s):
        Formula split regex: https://stackoverflow.com/questions/27808112/java-splitting-with-math-expression
*/

package com.example.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.os.Bundle;

import com.example.calculator.R;

public class MainActivity extends AppCompatActivity {

    private String mathString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mathString = "500*30^2*ln(50)";

        Expression e = new Expression(mathString);
        mXparser.consolePrintln("Res: " + e.getExpressionString() + " = " + e.calculate());
    }
}