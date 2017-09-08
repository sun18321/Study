package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        init();
    }

    private void init() {
        System.out.println("init");
        Log.d("123", "第一个log");
        Log.d("asd", "第二个log");
        Log.d("qwe", "第三个log");
        Log.d("zxc", "第四个log");
        Log.d("sp", "第五个log");
    }
}
