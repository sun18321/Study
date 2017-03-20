package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class ScreenClickActivity extends AppCompatActivity {

    private TextView mTime_plus;
    private TextView mTime_minus;
    private Timer mTimer;
    private int time = 0;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_click);

        init();
        initTime();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                System.out.println("up");
                time = 0;
                break;
            case MotionEvent.ACTION_DOWN:
                System.out.println("down");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("move");
                break;
        }
//        time = 0;
        return super.onTouchEvent(event);
    }

    private void initTime() {

    }

    private void init() {
        mTime_plus = (TextView) findViewById(R.id.time_plus);
        mTime_minus = (TextView) findViewById(R.id.time_minus);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTime_plus.setText("" + time);
                        time++;
                        if (time >= 5) {
                            Toast.makeText(ScreenClickActivity.this, "到时间了", Toast.LENGTH_SHORT).show();
                            System.out.println("到时间了");
                        }
                    }
                });
            }
        };
        mTimer = new Timer();
        mTimer.schedule(timerTask, 1000, 1000);
    }
}
