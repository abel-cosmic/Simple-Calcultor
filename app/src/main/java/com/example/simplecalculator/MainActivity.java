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
public class MainActivity extends AppCompatActivity {
    static boolean clear = false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
                    EditText equationEditText = (EditText) findViewById(R.id.equation);// the upper equation displaying edit text
                    EditText resultEditText = (EditText) findViewById(R.id.answer);// the lower result displaying edit text
                    equationEditText.setInputType(InputType.TYPE_NULL);
                    performClick(v, equationEditText, resultEditText);
                }
            });
        }
    }
    private void performClick(View v, EditText equationEditText, EditText resultTextView) {//onclick listener
        String currentEquation = equationEditText.getText().toString();
        String buttonValue = ((Button) v).getText().toString();
        String equation = equationEditText.getText().toString();
        if (buttonValue.equals("AC")) {// - performs all clear from both text views
            equationEditText.setText("");
            resultTextView.setText("");
        }else if(clear){// performs can't divide by zero concept
           if(buttonValue.equals("=")){//checks if equals button is clicked while division by zero is applied
               resultTextView.setText("");
               if(!equationEditText.getText().toString().isEmpty()){
                   resultTextView.setText("");
                   Toast.makeText(this, "Can't divide by zero.", Toast.LENGTH_SHORT).show();
               }
               clear = false;
           }
            if (buttonValue.equals("C")) {// -deletes the last entry while division by zero is applied
                if (!equation.isEmpty()) {
                    equationEditText.setText(equation.substring(0, equation.length()-2));
                    resultTextView.setText(calculate(equationEditText.getText().toString()));
                }
                clear= false;
                return;
            }
           return;
        } else if ((currentEquation.endsWith("/")||currentEquation.endsWith("+") ||currentEquation.endsWith("-")||currentEquation.endsWith("*"))&& buttonValue.equals("=")) {
            // checks if equals is clicked while suffix is operation
            resultTextView.setText("");
            Toast.makeText(this, "Invalid format used.", Toast.LENGTH_SHORT).show();
            resultTextView.setText("");
            return;

        } else if (buttonValue.equals("=")) {// -displays the end result
            try {
                if(currentEquation.isEmpty()){// if we tried to see the result with out inputting anything
                    Toast.makeText(this, "Invalid format used.", Toast.LENGTH_SHORT).show();
                    return;
                }
                double result = Double.parseDouble(calculate(currentEquation));
                equationEditText.setText(Double.toString(result));
                resultTextView.setText("");
            } catch (Exception e) {
                Toast.makeText(this, "Invalid Equation.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else if (buttonValue.equals("C")) {// -deletes the last entry
            if (!equation.isEmpty()) {
                equationEditText.setText(equation.substring(0,equation.length()-1));
                resultTextView.setText(calculate(equationEditText.getText().toString()));
            } else if (equationEditText.getText().toString().equals("")) {
                resultTextView.setText("");
            }
        }
        else if (buttonValue.equals("-") && currentEquation.isEmpty()) {// makes inputting negative number first applicable
            equationEditText.setText("-");
        }
        else if (buttonValue.matches("[+\\-*/.]") && !currentEquation.isEmpty()) {// checks if the button is inputting operations
            String s = currentEquation.substring(0, currentEquation.length() - 1) + buttonValue;
            if ((currentEquation.endsWith("-"))||(currentEquation.endsWith("+"))||(currentEquation.endsWith("*"))||(currentEquation.endsWith("/"))||(currentEquation.endsWith("."))) {
                // to append operations if the user wants to change it
                if(buttonValue.equals(".")){//if the user inputs . only to add prefix 0 while the equation is not empty
                    equationEditText.setText(currentEquation+"0"+buttonValue);
                    return;
                }
                currentEquation = s;
                equationEditText.setText(currentEquation);
                return;
            }
            if(buttonValue.equals(".") && currentEquation.endsWith("/")){//adds prefix 0
                equationEditText.setText(currentEquation +"0"+ buttonValue);
                return;
            }
            equationEditText.setText(currentEquation + buttonValue);
            resultTextView.setText(calculate(equationEditText.getText().toString()));
        }
        else if((buttonValue.matches("[+\\-*/]")) && currentEquation.isEmpty()){//not to add operation while the equation is empty
            Toast.makeText(this, "Invalid format used.", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(currentEquation.isEmpty() && buttonValue.equals(".")){//to add 0 as prefix if the equation is empty
            equationEditText.setText("0"+buttonValue);
        }
        else if (buttonValue.equals(".")&& !currentEquation.matches("[+\\-*/.]$")){ // to append the integers/operands into double
            equationEditText.setText(currentEquation + buttonValue);
        }
        else if(buttonValue.equals(".")){// handles if the user adds dot before doing anything
             if(currentEquation.isEmpty()){
                 if(currentEquation.endsWith("/")){
                     Toast.makeText(this, "Illegal start of equation", Toast.LENGTH_SHORT).show();
                     return;
                 }else if(currentEquation.endsWith("+")){
                     Toast.makeText(this, "Illegal start of equation", Toast.LENGTH_SHORT).show();
                     return;
                 }else if(currentEquation.endsWith("*")){
                     Toast.makeText(this, "Illegal start of equation", Toast.LENGTH_SHORT).show();
                     return;
                 }else if(currentEquation.endsWith("-")){
                     Toast.makeText(this, "Illegal start of equation", Toast.LENGTH_SHORT).show();
                     return;
                 }
            }
             if(currentEquation.endsWith(".") && !currentEquation.isEmpty()){
                equationEditText.setText(currentEquation);
                resultTextView.setText("");
            }
            if(currentEquation.endsWith("/")){
                equationEditText.setText(currentEquation+"0"+ buttonValue);
            }
            if(currentEquation.contains(".")){
                resultTextView.setText("");
                equationEditText.setText("");
            }
        }
        else {
            equationEditText.setText(currentEquation + buttonValue);
            resultTextView.setText(calculate(equationEditText.getText().toString()));
        }
    }
    public String calculate(String input) {
        String[] operands = input.split("[+\\-*/]");
        String[] operators = input.replaceAll("[^+\\-*/]", "").split("");
        double result = 0;
        boolean firstOperand = true;
        for (int i = 0; i < operands.length; i++) {
            double operand = 0;
            if (operands[i].isEmpty()) {
                operand = 0;
            } else {
                try{
                    operand = Double.parseDouble(operands[i]);
                }catch(Exception e){
                    return "invalid format";
                }
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
                        try{
                            if(operand != 0){
                                result /= operand;
                            }
                            clear= true;
                        }catch (ArithmeticException e ){
                            Toast.makeText(getApplicationContext(),"can't divide by zero",Toast.LENGTH_SHORT);
                        }
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