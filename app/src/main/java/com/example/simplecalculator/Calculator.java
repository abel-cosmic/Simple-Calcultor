package com.example.simplecalculator;
import android.widget.EditText;

import java.util.Locale;
/*
* TODO: the methods i made to are considering the following fuctionality:
*  - when clicking . it will change it into double that's why i use enterDigit method value
*  - when the calculator is performing a calculation it uses performOperation method
*  - when the calculator wants to erase current value or clear all
*  - when the calculator wants to see the answer it uses getResult method
*
*
* */
public class Calculator {
    private String currentValue = null;
    private double input = 0.0;
    private double result = 0.0;

    private boolean decimalEntered = false;

    public Calculator() {
        this.currentValue = currentValue;
        this.result = result;
        this.input = input;
    }
    public void enterDigit(int digit) {

        input = input * 10 + digit;
    }
    public void enterDecimal() {
        if (!decimalEntered) {
            input = input + 0.1;
            decimalEntered = true;
        }
    }
    private double add(double a, double b) {
        return a + b;
    }

    private double subtract(double a, double b) {
        return a - b;
    }

    private double multiply(double a, double b) {
        return a * b;
    }

    private double divide(double a, double b) {
        return a / b;
    }
    public void performOperation(String operationSymbol) {
        double value = Integer.parseInt(currentValue);
        switch (operationSymbol) {
            case "+":
                result = add(value, input);
                break;
            case "-":
                result = subtract(value, input);
                break;
            case "×":
                result = multiply(value, input);
                break;
            case "÷":
                result = divide(value, input);
                break;
            case "=":
                input = value;
                getResult();

        }
    }
    void updateDisplay() {
        String text;
        if (decimalEntered) {
            text = String.format(Locale.getDefault(), "%.1f", input);
        } else {
            int intValue = (int) input;
            if (input == intValue) {
                text = String.valueOf(intValue);
            } else {
                text = String.valueOf(input);
            }
        }
    }
    public void clear() {
        double value = Integer.parseInt(currentValue);
        value = 0.0;
        input = 0.0;
        result = 0.0;
        decimalEntered = false;
        updateDisplay();
    }

    public void clearEntry() {
        input = 0.0;
        decimalEntered = false;
        updateDisplay();
    }

    public void clearAll() {
        double value = Integer.parseInt(currentValue);
        value = 0.0;
        clearEntry();
        result = 0.0;
    }


    public double getCurrentValue() {
        double value = Integer.parseInt(currentValue);
        return value;
    }


    public double getResult() {
        return result;
    }

}
