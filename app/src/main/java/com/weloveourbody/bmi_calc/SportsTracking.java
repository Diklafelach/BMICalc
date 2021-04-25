package com.weloveourbody.bmi_calc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SportsTracking extends AppCompatActivity {
    Button startTimerButton;
    Button stopTimerButton;
    Chronometer timerResult;
    EditText displayTimeResult;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    static double time;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_tracking);
        findViews();
        setParameters();
        setupButtonClickListener();
    }
    private void findViews() {
        displayTimeResult = findViewById(R.id.edit_text_Result);
        timerResult = findViewById(R.id.edit_timer);
        startTimerButton = findViewById(R.id.button_start);
        stopTimerButton = findViewById(R.id.button_stop);
    }

    private void setParameters() {
        pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        if (time == 0.0)
            time = (double) pref.getFloat(String.valueOf(time), 0);
        else
            time = (double) pref.getFloat(String.valueOf(time), (float) time);
        editor = pref.edit();
        editor.commit();
        calcMinSecHour();
    }

    private void setupButtonClickListener() {
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    timerResult.setBase(SystemClock.elapsedRealtime());
                    timerResult.start();
                    running = true;
                }

            }

        });
        stopTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running) {
                    timerResult.stop();
                    long result = SystemClock.elapsedRealtime() - timerResult.getBase();
                    time = time + result / 1000.0;
                    timerResult.setBase(SystemClock.elapsedRealtime());
                    running = false;
                    displayingTimeResult(time);
                }
            }
        });
    }

    public void displayingTimeResult(double time) {
        time = (double) pref.getFloat(String.valueOf(time), (float) time);
        editor = pref.edit();
        editor.commit();
        calcMinSecHour();
    }

    private void calcMinSecHour()
    {
        if(time/3600 >=1)
            displayTimeResult.setText("The amount of time I did sports this week is " + String.format("%.1f", time/3600.0) + " " + "seconds");
        else if (time / 60 >= 1)
            displayTimeResult.setText("The amount of time I did sports this week is " + String.format("%.1f", time/60.0) + " " + "seconds");
        else
            displayTimeResult.setText("The amount of time I did sports this week is " + String.format("%.0f", time) + " " + "minutes");
    }

    public void onBackPressed() {
        Intent intent = new Intent(SportsTracking.this, MainActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_main);
    }
}