package com.example.foregroundserviceexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private EditText editTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextInput = findViewById(R.id.edit_text_input);
        Handler mHandler = new Handler();
    }


    public void startService(View v) {
        Timer timerObj = new Timer();
        TimerTask timerTaskObj = new TimerTask() {
            public void run() {
                //perform your action here
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView tdate=(TextView)findViewById(R.id.textview_time);
                        long date=System.currentTimeMillis();
                        SimpleDateFormat sdf=new SimpleDateFormat("hh-mm a");
                        String dateString=sdf.format(date);
                        tdate.setText(dateString);
                    }
                });
            }
        };
        timerObj.schedule(timerTaskObj, 0, 5000);

        String input = editTextInput.getText().toString();

        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra("inputExtra", input);

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService(View v) {
        Intent serviceIntent = new Intent(this, ExampleService.class);
        stopService(serviceIntent);
    }

}
//test comment
