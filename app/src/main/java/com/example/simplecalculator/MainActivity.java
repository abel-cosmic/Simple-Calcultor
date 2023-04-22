package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
*
* */
/*
* TODO: the button names should be the name of the numbers like: 1 == one....
*  numbered Buttons : 1,2,3,4,5,6,7,8,9,0
*  decimal button : .
*  operation buttons : +, - , / , *
*  extra button : CLEAR
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}