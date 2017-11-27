package com.sun.mystudy;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class NewRefreshActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_refresh);

        initView();
    }

    private void initView() {
        mFrameLayout = (FrameLayout) findViewById(R.id.fram);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_bottom);



    }
}
