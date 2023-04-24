package com.example.simplecalculator;

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
    private double currentValue = 0.0;
    private String value = String.valueOf(currentValue);

    public String getValue() {
        return null;
    }

    public void setValue(String value) {
        this.value = value;
    }

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
    private double add() {
        return (currentValue + input);
    }

    private double subtract() {

        return (currentValue - input);
    }

    private double multiply() {
        return (currentValue * input);
    }

    private double divide() {

        return (currentValue / input);
    }

    public void performOperation(String operationSymbol) {
        switch (operationSymbol) {
            case "+":
                result = add();
                break;
            case "-":
                result = subtract();
                break;
            case "x":
                result = multiply();
                break;
            case "/":
                result = divide();
                break;
            case "=":
                result = getResult();
                break;
        }
        currentValue = result;
        input = 0.0;
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
        currentValue = 0.0;
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
        currentValue = 0.0;
        clearEntry();
        result = 0.0;
    }

    public double getResult() {return result;}
}
