package com.sun.mystudy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BroadcastReceiverActivity extends AppCompatActivity {

    private MyReceiver mMyReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        init();
    }

    private void init() {
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.int");
                intent.putExtra("int", 100);
                sendBroadcast(intent);
            }
        });

        mMyReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.int");
        registerReceiver(mMyReceiver, intentFilter);
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int anInt = intent.getIntExtra("int", 0);
            System.out.println("广播收到的" + anInt);
        }
    }
}
