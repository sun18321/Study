package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;

public class RatingActivity extends AppCompatActivity {

    private RatingBar mRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        initView();
    }

    private void initView() {
        mRate = (RatingBar) findViewById(R.id.rate);
        mRate.setNumStars(5);
        mRate.setRating(4.2f);
    }
}
