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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class SportsTracking extends AppCompatActivity {
    Button startTimerButton;
    Button stopTimerButton;
    Chronometer timerResult;
    EditText displayTimeResult;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    static double time;
    boolean running = false;
    File fileSharedPrefs;

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
        fileSharedPrefs = new File("/data/data/" + getPackageName() +  "/shared_prefs/MyPref.xml" );
        if(fileSharedPrefs.exists())
        {

            // 1-sun 2-mon 3-tues 4-wed 5-thurs 6-fri 7-sat
            String date =  String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
            if(date.equals("7"))
            {
                WriteToFile(fileSharedPrefs,0.0);
                displayTimeResult.setText("On Saturday we rest");
            }
            else
                ReadFromFile(fileSharedPrefs);
        }
        else
        {
            WriteToFile(fileSharedPrefs,0.0);
        }
        editor = pref.edit();
        editor.apply();
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
                    updatePrefTime();
                    calcMinSecHour();
                }
            }
        });
    }

    public void updatePrefTime() {
        time = (double) pref.getFloat(String.valueOf(time), (float) time);
        editor = pref.edit();
        editor.apply();
    }

    private void calcMinSecHour()
    {
        if(time/3600 >=1)
            displayTimeResult.setText("The amount of time I did sports this week is " + String.format("%.1f", time/3600.0) + " " + "hours");
        else if (time / 60 >= 1)
            displayTimeResult.setText("The amount of time I did sports this week is " + String.format("%.1f", time/60.0) + " " + "minutes");
        else
            displayTimeResult.setText("The amount of time I did sports this week is " + String.format("%.0f", time) + " " + "seconds");
        WriteToFile(fileSharedPrefs,time);
    }

    private void WriteToFile(File files,double context)
    {
        FileWriter writer;
        try {
            writer = new FileWriter(files);
            writer.write(String.valueOf(context));
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private  void ReadFromFile(File files)
    {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(files));
            String line = String.valueOf(reader.readLine());
            time=Double.parseDouble(line);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SportsTracking.this, MainActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_main);
        finish();
    }
}