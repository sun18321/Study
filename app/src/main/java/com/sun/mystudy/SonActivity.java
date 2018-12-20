package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sun.base.BaseActivity;

public class SonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("extend", "son oncreate");
    }

    @Override
    public int getLayouId() {
        Log.d("extend", "son getlay");
        return R.layout.activity_son;
    }

    @Override
    public void initViews() {
        Log.d("extend", "son initview");
    }
}
