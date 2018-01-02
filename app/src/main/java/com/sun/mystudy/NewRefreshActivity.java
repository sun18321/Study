package com.sun.mystudy;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.sun.fragment.FirstFragment;
import com.sun.fragment.SecondFragment;
import com.sun.fragment.ThirdFragment;

import java.util.HashMap;

public class NewRefreshActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FrameLayout mFrameLayout;
    private BottomNavigationView mNavigationView;
    private FirstFragment mFirstFragment;
    private SecondFragment mSecondFragment;
    private ThirdFragment mThirdFragment;
    private HashMap<Integer, Fragment> mMap = new HashMap<>();
    private HashMap<Integer, String> mTitleMap = new HashMap<>();
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_refresh);

        initView();
    }

    private void initView() {
        mFirstFragment = new FirstFragment();
        mSecondFragment = new SecondFragment();
        mThirdFragment = new ThirdFragment();
        mMap.put(R.id.bottom_first, mFirstFragment);
        mMap.put(R.id.bottom_second, mSecondFragment);
        mMap.put(R.id.bottom_third, mThirdFragment);
        mTitleMap.put(R.id.bottom_first, "first");
        mTitleMap.put(R.id.bottom_second, "second");
        mTitleMap.put(R.id.bottom_third, "third");

        mFrameLayout = (FrameLayout) findViewById(R.id.fram);
        mNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bottom);
        mToolbar = (Toolbar) findViewById(R.id.tab);
        mNavigationView.setOnNavigationItemSelectedListener(this);
        mNavigationView.setSelectedItemId(R.id.bottom_second);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fram, mMap.get(item.getItemId())).commit();
        mToolbar.setTitle(mTitleMap.get(item.getItemId()));
        return true;

    }

}
