package com.sun.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sun.mystudy.R;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getLayouId();

    public abstract void initViews();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("extend", "father oncreate");

        getLayouId();
        int layout = getLayouId();
        setContentView(layout);
        initViews();
    }

}
