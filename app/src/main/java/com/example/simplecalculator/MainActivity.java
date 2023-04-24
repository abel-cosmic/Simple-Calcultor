package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private String temp;
    private int number,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create the object of calculator
        Button[] digitButtons;
        Button[] operationButtons;

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
                    EditText equation=(EditText) findViewById(R.id.equation);
                    temp = String.valueOf(equation.getText());
                    equation.setText(temp + digit);
                    calculator.updateDisplay();
                }
            });
        }

        // Initialize operation buttons

        operationButtons = new Button[5];
        operationButtons[0] = findViewById(R.id.adder);
        operationButtons[1] = findViewById(R.id.minus);
        operationButtons[2] = findViewById(R.id.divide);
        operationButtons[3] = findViewById(R.id.multiply);
        operationButtons[4] = findViewById(R.id.equals);

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
                    operationSymbol = "/";
                    break;
                case 3:
                    operationSymbol = "x";
                    break;
                case 4:
                    operationSymbol = "=";
                    break;
                default:
                    operationSymbol = "";
                    break;
            }
            operationButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculator.performOperation(operationSymbol);
                    calculator.updateDisplay();
                    EditText answer = (EditText) findViewById(R.id.answer);
                    answer.setText(String.valueOf(calculator.getResult()));
                    EditText equation=(EditText) findViewById(R.id.equation);
                    temp = String.valueOf(equation.getText());
                    if(calculator.getResult() == 0.0){
                        answer.setText(calculator.getValue());
                    }
                    if(operationSymbol == "="){
                        equation.setText(String.valueOf(calculator.getResult()));
                        answer.setText(" ");
                    }else{
                        equation.setText(temp + operationSymbol);
                    }

                }
            });
        }
        //for displaying the equation portion only

        Button btnAc = (Button)findViewById(R.id.AC);
        Button btnC=(Button)findViewById(R.id.cancel);
        Button btnDot=(Button) findViewById(R.id.dot);
        // Initialize extras buttons
        btnDot.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        calculator.enterDecimal();
                        calculator.updateDisplay();
                        EditText displayView = (EditText) findViewById(R.id.answer);
                        if(calculator.getResult() == 0.0){
                            displayView.setText(calculator.getValue());
                        }
                        displayView.setText(String.valueOf(calculator.getResult()));
                    }
                }
        );
        btnAc.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        calculator.clearAll();
                        calculator.updateDisplay();
                        EditText equation = (EditText) findViewById(R.id.equation);
                        EditText answer = (EditText) findViewById(R.id.answer);
                        answer.setText(" ");
                        equation.setText(" ");
                    }
                }
        );
        btnC.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        calculator.clearEntry();
                        calculator.updateDisplay();
                        EditText equation = (EditText) findViewById(R.id.equation);
                        EditText answer = (EditText) findViewById(R.id.answer);
                        if(calculator.getResult() == 0.0){
                            equation.setText(" ");
                            answer.setText(String.valueOf(calculator.getResult()));
                        }else{
                            equation.setText(String.valueOf(calculator.getResult()));
                        }

                    }
                }
        );
    }
}