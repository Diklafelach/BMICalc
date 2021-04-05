package com.weloveourbody.bmi_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the content of the screen.
        //R.layout.* references any layout resource that have been create
        setContentView(R.layout.activity_main);
    }
}