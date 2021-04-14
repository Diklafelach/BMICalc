package com.weloveourbody.bmi_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

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
            public void onClick(View v)
            {
               double bmiResult =  calculateBmi();
                displayResult(bmiResult);
            }
        });
    }

    private double calculateBmi() {
        String ageText = ageEditText.getText().toString();
        String weightText = weightEditText.getText().toString();
        String heightText = heightEditText.getText().toString();

        int age = Integer.parseInt(ageText);
        int weight = Integer.parseInt(weightText);
        double height = Integer.parseInt(heightText)/100.0;
        return weight / (height * height);

    }
    private void displayResult(double bmi)
    {
        DecimalFormat myDecimalFormat = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormat.format(bmi);
        String fullResultString;
        if(bmi<18.5)
            fullResultString= bmiTextResult +" "+ "You are underweight";
        else if(bmi>=18.5 && bmi<=25)
            fullResultString=bmiTextResult +" " +"You are on a proper weight";
        else if(bmi>25 && bmi<=29.9)
            fullResultString=bmiTextResult + " "+"You are on an over-weight";
        else if(bmi>=30 && bmi<=40)
            fullResultString=bmiTextResult +" "+ "you are on an obesity";
        else
            fullResultString=bmiTextResult +" "+ "you are on an acute obesity";
        result.setText(fullResultString);
    }

}