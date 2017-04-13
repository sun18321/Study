package com.sun.mystudy;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;

public class ChronometerActivity extends AppCompatActivity implements View.OnClickListener {

    private Chronometer mChronometer;
    private int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chronometer);

        init();
    }

    private void init() {
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.end).setOnClickListener(this);
        findViewById(R.id.restart).setOnClickListener(this);
        findViewById(R.id.exit).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        time++;
                        System.out.println("时间-------" + chronometer.getBase());
                        System.out.println("时间system-------" + SystemClock.elapsedRealtime());
                        System.out.println("mytime-------" + time);
                    }
                });


                mChronometer.start();



                break;
            case R.id.end:
                mChronometer.stop();
                break;
            case R.id.restart:
                mChronometer.setBase(SystemClock.elapsedRealtime());
                break;
            case R.id.exit:
                ChronometerActivity.this.finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("调用了distroy");
    }
}
