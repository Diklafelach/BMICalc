package com.weloveourbody.bmi_calc;

import android.content.Intent;
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
    double time=0;
    boolean running=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_tracking);
        findViews();
        displayTimeResult.setText("The amount of time I did sports this week is 0");
        setupButtonClickListener();
      // ActionBar actionBar = getSupportActionBar();
       // actionBar.setDisplayHomeAsUpEnabled(true);


    }

    private  void findViews()
    {
        displayTimeResult=findViewById(R.id.edit_text_Result);
        timerResult=findViewById(R.id.edit_timer);
        startTimerButton=findViewById(R.id.button_start);
        stopTimerButton=findViewById(R.id.button_stop);
    }
    private void setupButtonClickListener() {
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!running)
                {
                   timerResult.setBase(SystemClock.elapsedRealtime());
                    timerResult.start();
                    running=true;
                }

            }

        });
        stopTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(running) {
                    timerResult.stop();
                    long t,g;
                    t=SystemClock.elapsedRealtime();
                    g=timerResult.getBase();
                    time =  time +( t-g)/1000.0;
                    running = false;
                    timerResult.setBase(SystemClock.elapsedRealtime());
                    displayingTimeResult(time);
                }
            }
        });
    }
    public void displayingTimeResult(double time)
    {
        displayTimeResult.setText("The amount of seconds I did sports this week is " + (int)(time/60));
    }

    public void onBackPressed() {
        Intent intent = new Intent(SportsTracking.this, MainActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_main);
    }
}