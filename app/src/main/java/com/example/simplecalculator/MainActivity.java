package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/*
 * TODO: use one of the following layouts:
 *  - LinearLayout
 *  - AbsoluteLayout
 *  - RelativeLayout
 *  - FrameLayout
 *  - ScrollView
 *
 * */
/*
 * TODO: we have to do the following functionalities
 *  - addition
 *  - substraction
 *  - division
 *  - multiplication
 *  - clear
 *  - all clear
 *
 * */
/*
 * TODO: the button names should be the name of the numbers like: 1 == one....
 *  numbered Buttons : 1,2,3,4,5,6,7,8,9,0
 *  decimal button : .
 *  operation buttons : +, - , / , *
 *  extra button : CLEAR, All CLEAR
 * */
public class MainActivity extends AppCompatActivity {
    private double num1 = 0.0;
    private double num2 = 0.0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create the object of calculator
        Button[] digitButtons;
        Button[] buttons = new Button[] {
                findViewById(R.id.zero),
                findViewById(R.id.one),
                findViewById(R.id.two),
                findViewById(R.id.three),
                findViewById(R.id.four),
                findViewById(R.id.five),
                findViewById(R.id.six),
                findViewById(R.id.seven),
                findViewById(R.id.eight),
                findViewById(R.id.nine),
                findViewById(R.id.adder),
                findViewById(R.id.minus),
                findViewById(R.id.multiply),
                findViewById(R.id.divide),
                findViewById(R.id.AC),
                findViewById(R.id.cancel),
                findViewById(R.id.dot),
                findViewById(R.id.equals)
        };
        for (Button button : buttons) {
            button.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditText equationEditText = (EditText) findViewById(R.id.equation);
                            EditText resultEditText = (EditText) findViewById(R.id.answer);
                            performClick(v, equationEditText, resultEditText);
                        }
                    });
        }



    }
    private void performClick(View view, EditText equationEditText, EditText resultEditText) {
        Button button = (Button) view;
        String text = button.getText().toString();

        if (text.matches("[0-9.]")) {

            // Handle digit and decimal point button click event
            String equation = equationEditText.getText().toString();
            equationEditText.setText(equation + text);
            resultEditText.setText(""); // Clear result

        } else if (text.matches("[+\\-*/]")) {

            // Handle operator button click event

            if (!equationEditText.getText().toString().isEmpty()) {
                num1 = Double.parseDouble(equationEditText.getText().toString());
                operator = text;
                equationEditText.setText(""); // Clear expression
                resultEditText.setText(num1 + " " + operator + " ");//show equation with out the result and equals sign.

            }

        } else if (text.equals("=")) {

            // Handle equals button click event

            if (!(operator.isEmpty())) {
                num2 = Double.parseDouble(equationEditText.getText().toString());
                double result = performOperation(num1, num2, operator);
                equationEditText.setText(num1 + " " + operator + " " + num2); // Show full equation with result
                resultEditText.setText("= " + result);// Show result only
                num1 = result; // Store result as num1 for subsequent calculations
                operator = "";
            }
        } else if (text.equals("C")) {

            // Handle clear button click event
            String equation = equationEditText.getText().toString();
            if (!equation.isEmpty()) {
                String before = equation.substring(0, equation.length() - 1);
                resultEditText.setText(before);
            }
        }
        else if (text.equals("AC")) {

            // Handle all clear button click event

            num1 = 0;
            num2 = 0;
            operator = "";
            equationEditText.setText("");
            resultEditText.setText("");
        }
        else if (text.equals(".")) {
            // Handle decimal point button click event
            String equation = equationEditText.getText().toString();
            if (equation.isEmpty()) {
                equationEditText.setText("0.");
            } else if (!equation.contains(".")) {
                equationEditText.setText(equation + ".");
            }
        }
    }
    private double performOperation(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return Double.NaN;
                }
            default:
                return 0.0;
        }
    }
}
