package com.sun.mystudy;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sun.myclass.MyService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceActivity extends AppCompatActivity {

    private MyService.MyBind mMyBind;

    @BindView(R.id.start)
    Button mStart;
    @BindView(R.id.stop)
    Button mStop;
    @BindView(R.id.bind)
    Button mBind;
    @BindView(R.id.unbind)
    Button mUnbind;
    private ServiceConnection mConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);

        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mMyBind = (MyService.MyBind) service;
                mMyBind.startBind();
                mMyBind.getProgress();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };


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
            case R.id.bind:
                Intent bind_intent = new Intent(ServiceActivity.this, MyService.class);
                bindService(bind_intent, mConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindService(mConnection);
                break;
        }
    }
}
