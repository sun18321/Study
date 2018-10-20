package com.sun.mystudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DesignActivity extends AppCompatActivity {


    @BindView(R.id.countdown)
    Button mCountdown;
    @BindView(R.id.six)
    Button mSix;
    @BindView(R.id.click)
    Button mClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.countdown, R.id.six, R.id.click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.countdown:
                startActivity(new Intent(this, CountDownActivity.class));
                break;
            case R.id.six:
                startActivity(new Intent(this, SixAngleActivity.class));
                break;
            case R.id.click:
                startActivity(new Intent(this, ClickDrawActivity.class));
                break;
        }
    }
}
