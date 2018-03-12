package com.sun.mystudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sun.myclass.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceActivity extends AppCompatActivity {

    @BindView(R.id.start)
    Button mStart;
    @BindView(R.id.stop)
    Button mStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.start, R.id.stop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                Intent start_intent = new Intent(ServiceActivity.this, MyService.class);
                startService(start_intent);
                break;
            case R.id.stop:
                Intent stop_intent = new Intent(ServiceActivity.this, MyService.class);
                stopService(stop_intent);
                break;
        }
    }
}
