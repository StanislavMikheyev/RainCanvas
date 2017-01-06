package com.stanislavmikheyev.raincanvas;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private RainView rainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rainView = (RainView) findViewById(R.id.rain_view);

        final Handler handler = new Handler(Looper.getMainLooper());
        Runnable movePlayer0Runnable = new Runnable(){
            public void run(){
                rainView.invalidate();
                handler.postDelayed(this, 50);
            }
        };
        movePlayer0Runnable.run();

    }

    public void buttonClick(View view) {
        rainView.invalidate();
    }
}
