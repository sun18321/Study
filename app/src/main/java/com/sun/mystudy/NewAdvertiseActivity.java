package com.sun.mystudy;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.sun.advertisetwo.HorizontalInfiniteCycleViewPager;
import com.sun.advertisetwo.HorizontalPagerAdapter;

import me.zhanghai.android.materialprogressbar.HorizontalProgressDrawable;

public class NewAdvertiseActivity extends AppCompatActivity {

    private HorizontalInfiniteCycleViewPager mViewpager;
    private int num = 0;
    int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advertise);

        initView();
    }

    private void initView() {
        mViewpager = (HorizontalInfiniteCycleViewPager) findViewById(R.id.viewpager);
        mViewpager.setAdapter(new HorizontalPagerAdapter(this, false));
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                int i = num % 6;
                System.out.println("current" + current + 1);
                mViewpager.setCurrentItem(current + 1, true);
            }
        });

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 7) {
                    current = 1;
                } else if (position == 0) {
                    current = 6;
                } else {
                    current = position;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://手指按下
                        System.out.println("手指按下");
                        break;
                    case MotionEvent.ACTION_UP://手指抬起
                        System.out.println("手指抬起");
                        break;
                }
                    return false;
                }
        });
    }
}
