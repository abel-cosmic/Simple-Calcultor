package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Resources;
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
    private Calculator calculator;
    private EditText displayView;
    private Button[] digitButtons;
    private Button[] operationButtons;
    private Button btnAc,btnC,btnDot;
    @Override
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create the object of calculator

        calculator = new Calculator();

        // Initialize digit buttons
        digitButtons = new Button[10];
        digitButtons[0] =  findViewById(R.id.zero);
        digitButtons[1] =  findViewById(R.id.one);
        digitButtons[2] =  findViewById(R.id.two);
        digitButtons[3] = findViewById(R.id.three);
        digitButtons[4] = findViewById(R.id.four);
        digitButtons[5] = findViewById(R.id.five);
        digitButtons[6] = findViewById(R.id.six);
        digitButtons[7] = findViewById(R.id.seven);
        digitButtons[8] = findViewById(R.id.eight);
        digitButtons[9] = findViewById(R.id.nine);

         // add an onClick listener for each digitButton
        for (int i = 0; i < digitButtons.length; i++) {
            int digit = i;
            digitButtons[i].setOnClickListener(
                    new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculator.enterDigit(digit);
                    calculator.updateDisplay();
                }
            });
        }

        // Initialize operation buttons

        operationButtons = new Button[4];
        operationButtons[0] = findViewById(R.id.adder);
        operationButtons[1] = findViewById(R.id.minus);
        operationButtons[2] = findViewById(R.id.divide);
        operationButtons[3] = findViewById(R.id.multiply);

        // Set onClickListener for operation buttons

        for (int i = 0; i < operationButtons.length; i++) {
            final String operationSymbol;
            switch (i) {
                case 0:
                    operationSymbol = "+";
                    break;
                case 1:
                    operationSymbol = "-";
                    break;
                case 2:
                    operationSymbol = "×";
                    break;
                case 3:
                    operationSymbol = "÷";
                    break;
                default:
                    operationSymbol = "";
            }
            operationButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculator.performOperation(operationSymbol);
                    calculator.updateDisplay();
                }
            });
        }

        // Initialize extras buttons
        btnDot.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        calculator.enterDecimal();
                        calculator.updateDisplay();
                    }
                }
        );
        btnAc.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        calculator.clearAll();
                        calculator.updateDisplay();
                    }
                }
        );
        btnC.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        calculator.clear();
                        calculator.updateDisplay();
                    }
                }
        );

        displayView = (EditText) findViewById(R.id.answer);
        displayView.setText(String.valueOf(calculator.getResult()));
    }
}