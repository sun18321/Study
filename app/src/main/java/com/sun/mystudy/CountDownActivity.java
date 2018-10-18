package com.sun.mystudy;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.sun.design.CountDownView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountDownActivity extends AppCompatActivity {

    @BindView(R.id.count)
    CountDownView mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        ButterKnife.bind(this);

        mCount.startTime();
    }
}
