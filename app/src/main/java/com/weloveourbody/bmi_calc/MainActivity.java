package com.weloveourbody.bmi_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    //Fields
    TextView result;
    Button calculateButton;
    EditText ageEditText;
    EditText weightEditText;
    EditText heightEditText;
    RadioButton femaleButton;
    RadioButton maleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }


    private  void findViews()
    {
        //Connection between XML and JAVA
        result = findViewById(R.id.text_view_result);
        calculateButton = findViewById(R.id.button_calculate);
        ageEditText = findViewById(R.id.edit_text_age);
        weightEditText = findViewById(R.id.edit_text_weight);
        heightEditText = findViewById(R.id.edit_text_height);
        femaleButton = findViewById(R.id.radio_button_female);
        maleButton = findViewById(R.id.radio_button_male);
    }
    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();
            }
        });
    }

    private void calculateBmi() {
        String age = ageEditText.getText().toString();
        String weight = weightEditText.getText().toString();
        String height = heightEditText.getText().toString();
        double resultBmi;

    }

}