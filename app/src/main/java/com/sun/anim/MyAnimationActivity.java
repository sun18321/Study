package com.sun.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sun.mystudy.R;

public class MyAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mView_anim;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_animation);

        init();
    }

    private void init() {
        mInflater = LayoutInflater.from(MyAnimationActivity.this);
        mView_anim = (RelativeLayout) findViewById(R.id.view_anim);
        findViewById(R.id.car_anim).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.car_anim:
                View view_car = mInflater.inflate(R.layout.my_car, null);
                mView_anim.addView(view_car, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                anim1(view_car);
                break;
        }
    }

    public void anim1(final View view) {
        final ImageView front = (ImageView) view.findViewById(R.id.front_wheel);
        ImageView back = (ImageView) view.findViewById(R.id.back_wheel);

        ObjectAnimator.ofFloat(front, "rotationY", 0, 65).start();
//        ObjectAnimator.ofFloat(front, "rotationX", 0, 10).start();
        ObjectAnimator rotation = ObjectAnimator.ofFloat(front, "rotation", 0, -360);
        rotation.setDuration(100);
        rotation.setRepeatCount(ValueAnimator.INFINITE);
        rotation.setRepeatMode(ValueAnimator.RESTART);

        rotation.start();


        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", 800, 0);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", -200, 400);
        animatorX.setDuration(2500);
        animatorY.setDuration(2500);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


                anim2(view);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    public void anim2(View view) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", 0, -800);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", 400, 1000);
        animatorX.setDuration(1800);
        animatorY.setDuration(1800);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.start();
    }
}
