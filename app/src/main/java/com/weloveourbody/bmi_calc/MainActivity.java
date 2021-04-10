package com.weloveourbody.bmi_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the content of the screen.
        //R.layout.* references any layout resource that have been create
        setContentView(R.layout.activity_main);


        //Connection between XML and JAVA
        TextView result = findViewById(R.id.text_view_result);
        Button calculate = findViewById(R.id.button_calculate);
        EditText ageEditText = findViewById(R.id.edit_text_age);
        EditText weightEditText = findViewById(R.id.edit_text_weight);
        EditText heightEditText = findViewById(R.id.edit_text_height);
        RadioButton femaleButton = findViewById(R.id.radio_button_female);
        RadioButton maleButton = findViewById(R.id.radio_button_male);
    }
}