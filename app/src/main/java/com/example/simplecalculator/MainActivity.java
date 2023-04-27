package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/*
 *  use one of the following layouts:
 *  - LinearLayout
 *  - AbsoluteLayout
 *  - RelativeLayout
 *  - FrameLayout
 *  - ScrollView
 *
 * */
/*
 *  we have to do the following functionalities
 *  - addition
 *  - substraction
 *  - division
 *  - multiplication
 *  - clear
 *  - all clear
 *
 * */
/*
 *  the button names should be the name of the numbers like: 1 == one....
 *  numbered Buttons : 1,2,3,4,5,6,7,8,9,0
 *  decimal button : .
 *  operation buttons : +, - , / , *
 *  extra button : CLEAR, All CLEAR
 * */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Button[] buttons = new Button[]{
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
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get a reference to the system's AudioManager
                    AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    // Get a reference to the system's Vibrator
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Check if the device is in vibrate mode
                    if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE) {
                        // Vibrate the device for 50 milliseconds when the user taps a button
                        vibrator.vibrate(50);
                    }
                    EditText equationEditText = (EditText) findViewById(R.id.equation);
                    EditText resultEditText = (EditText) findViewById(R.id.answer);
                    equationEditText.setInputType(InputType.TYPE_NULL);
                    performClick(v, equationEditText, resultEditText);
                }
            });
        }


    }

    private void performClick(View v, EditText equationEditText, EditText resultTextView) {
        String currentEquation = equationEditText.getText().toString();
        String buttonValue = ((Button) v).getText().toString();
        String equation = equationEditText.getText().toString();
        if (buttonValue.equals("AC")) {// - performs all clear from both text views
            equationEditText.setText("");
            resultTextView.setText("");
        } else if (buttonValue.equals("=")) {// -displays the end result
            try {
                double result = Double.parseDouble(calculate(currentEquation));
                equationEditText.setText(Double.toString(result));
                resultTextView.setText("");
            } catch (Exception e) {
                resultTextView.setText("Invalid Equation");
            }
        } else if (buttonValue.equals("C")) {// -deletes the last entry
            if (!equation.isEmpty()) {
                equationEditText.setText(equation.substring(0, equation.length() - 1));
                resultTextView.setText(calculate(equationEditText.getText().toString()));
            }
        } else if (buttonValue.equals("-") && currentEquation.isEmpty()) {// makes inputting negative number first applicable
            equationEditText.setText("-");
        } else if (buttonValue.matches("[+\\-*/]")) {// checks if the button is inputting operations
            String s = currentEquation.substring(0, currentEquation.length() - 1) + buttonValue;
            if (currentEquation.endsWith("-")) {
               currentEquation = s;
               equationEditText.setText(currentEquation);
                return;
            }
            if (currentEquation.endsWith("+")) {
                currentEquation = s;
               equationEditText.setText(currentEquation);
                return;
            }
            if (currentEquation.endsWith("*")) {
                currentEquation = s;
               equationEditText.setText(currentEquation);
                return;
            }
            if (currentEquation.endsWith("/")) {
                currentEquation = s;
               equationEditText.setText(currentEquation);
                return;
            }
            equationEditText.setText(currentEquation + buttonValue);
            resultTextView.setText(calculate(equationEditText.getText().toString()));
        }
        else if (currentEquation.endsWith("/") && buttonValue.equals("0")) {// makes division by zero to display toast message and resets the last input and intermediate result to be reset too
            resultTextView.setText("");
            Toast.makeText(this, "can't divide by zero", Toast.LENGTH_LONG).show();
//TODO: append the operations if their was an operation already. like 8+-/* should be 8*
        }
//        else if (signs.contains(currentEquation.charAt(currentEquation.length() - 2)) && signs.contains(currentEquation.charAt(currentEquation.length() - 1))) {
//            currentEquation = currentEquation.substring(0, (currentEquation.length()-2) + (currentEquation.length() - 2));
//            equationEditText.setText(currentEquation + buttonValue);
//        }
        else {
            equationEditText.setText(currentEquation + buttonValue);
            resultTextView.setText(calculate(equationEditText.getText().toString()));
        }
    }

    public static String calculate(String input) {
        String[] operands = input.split("[+\\-*/]");
        String[] operators = input.replaceAll("[^+\\-*/]", "").split("");
        double result = 0;
        boolean firstOperand = true;
        for (int i = 0; i < operands.length; i++) {
            double operand;
            if (operands[i].isEmpty()) {
                operand = 0;
            } else {
                operand = Double.parseDouble(operands[i]);
            }
            if (firstOperand) {
                result = operand;
                firstOperand = false;
            } else {
                switch (operators[i - 1]) {
                    case "+":
                        result += operand;
                        break;
                    case "-":
                        result -= operand;
                        break;
                    case "*":
                        result *= operand;
                        break;
                    case "/":
                        result /= operand;
                        break;
                }
            }
        }
        if (result % 1 == 0) {
            // result is a whole number
            return Integer.toString((int) result);
        } else {
            // result is a decimal number
            return Double.toString(result);
        }
    }
}
