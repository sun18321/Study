package com.sun.mystudy;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sun.widget.MyfiveCorner;

public class MyFiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_five);

        MyfiveCorner five = (MyfiveCorner) findViewById(R.id.five);
//        five.setBackground();
    }
}
