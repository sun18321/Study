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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.countdown})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.countdown:
                startActivity(new Intent(this, CountDownActivity.class));
                break;
        }
    }
}
