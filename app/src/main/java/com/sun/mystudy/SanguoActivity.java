package com.sun.mystudy;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SanguoActivity extends AppCompatActivity {

    private ImageView mImg_cao;
    private ImageView mImg_liu;
    private ImageView mImg_sun;
    private int mHeight;
    private int mWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanguo);

        System.out.println("oncreate");

        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onresume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onstop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onpause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ondestroy");
    }

    private void init() {
        mImg_cao = (ImageView) findViewById(R.id.img_caocao);
        mImg_liu = (ImageView) findViewById(R.id.img_liubei);
        mImg_sun = (ImageView) findViewById(R.id.img_sunquan);

        ObjectAnimator animator = ObjectAnimator.ofFloat(mImg_cao, "scaleY", 1, 1.1f, 1);
        mImg_cao.setPivotX(175);
        mImg_cao.setPivotY(600);
        animator.setDuration(8000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();

        ObjectAnimator roration = ObjectAnimator.ofFloat(mImg_liu, "rotation", 0, 360, 0);
        roration.setDuration(5000);
        roration.setRepeatCount(ValueAnimator.INFINITE);
        roration.setRepeatMode(ValueAnimator.RESTART);
        mImg_liu.setPivotX(350);
        mImg_liu.setPivotY(600);
//        roration.start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        System.out.println("onwindowfocus");
        mHeight = mImg_liu.getHeight();
        mWidth = mImg_liu.getWidth();
        System.out.println("高" + mHeight + "宽" + mWidth);
    }
}
