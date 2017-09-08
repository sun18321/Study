package com.sun.mystudy;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TestAnimActivity extends AppCompatActivity {

    private ImageView mIv_anim;
    private Button mBtn_scale;
    private Button mBtn_scale_back;
    private ObjectAnimator mTranslateX;
    private Button mBtn_tranx;
    private Button mBtn_trany;
    private ObjectAnimator mTranslateY;
    private Button mBtn_single_tran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_anim);

        init();

    }

    private void init() {
        mIv_anim = (ImageView) findViewById(R.id.iv);
        mBtn_scale = (Button) findViewById(R.id.btn_scale);
        mBtn_scale_back = (Button) findViewById(R.id.btn_scale_back);
        mBtn_tranx = (Button) findViewById(R.id.btn_tranx);
        mBtn_trany = (Button) findViewById(R.id.btn_tranY);

        final PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX",  0, 1f);
        final PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY",  0, 1f);

        mBtn_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofPropertyValuesHolder(mIv_anim, pvhX, pvhY).setDuration(500).start();
            }
        });

        final PropertyValuesHolder pvhX_back = PropertyValuesHolder.ofFloat("scaleX", 1f, 0);
        final PropertyValuesHolder pvhY_back = PropertyValuesHolder.ofFloat("scaleY", 1f, 0);
        mBtn_scale_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofPropertyValuesHolder(mIv_anim, pvhX_back, pvhY_back).setDuration(500).start();
            }
        });


        final PropertyValuesHolder elementX = PropertyValuesHolder.ofFloat("translationX", 0, 100);
        final PropertyValuesHolder elementY = PropertyValuesHolder.ofFloat("translationY", 0, -100);
        final ObjectAnimator trans_right = ObjectAnimator.ofPropertyValuesHolder(mIv_anim, elementX, elementY);
        trans_right.setDuration(300);
        trans_right.setRepeatCount(3);
        trans_right.setRepeatMode(ValueAnimator.RESTART);


        final PropertyValuesHolder elementX_2 = PropertyValuesHolder.ofFloat("translationX", 0, -100);
        final PropertyValuesHolder elementY_2 = PropertyValuesHolder.ofFloat("translationY", 0, -100);
        final ObjectAnimator trans_left = ObjectAnimator.ofPropertyValuesHolder(mIv_anim, elementX_2, elementY_2);
        trans_left.setDuration(300);
        trans_left.setRepeatCount(3);
        trans_left.setRepeatMode(ValueAnimator.RESTART);



        mTranslateX = ObjectAnimator.ofFloat(mIv_anim, "translationX", 0, 500);
        mTranslateX.setDuration(300);
        mTranslateX.setRepeatCount(3);
        mTranslateX.setRepeatMode(ValueAnimator.RESTART);

        mTranslateY = ObjectAnimator.ofFloat(mIv_anim, "translationY", 0, 100);
        mTranslateY.setDuration(300);
        mTranslateY.setRepeatCount(3);
        mTranslateY.setRepeatMode(ValueAnimator.RESTART);


        final ValueAnimator valueAnimator = ValueAnimator.ofPropertyValuesHolder(elementX_2, elementY_2);
        valueAnimator.setDuration(300);




        mBtn_tranx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mTranslateX.start();'
//                trans_left.start();

                valueAnimator.setTarget(mIv_anim);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mIv_anim.setTranslationX((Float) animation.getAnimatedValue("translationX"));
                        mIv_anim.setTranslationY((float) animation.getAnimatedValue("translationY"));
                    }
                });
                valueAnimator.start();
            }
        });

        mBtn_trany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mTranslateY.start();
                trans_right.start();
            }
        });


        final ObjectAnimator animator = ObjectAnimator.ofFloat(mIv_anim, "translationX", -200, 0);
        mBtn_single_tran = (Button) findViewById(R.id.btn_tran);
        mBtn_single_tran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.start();
            }
        });


    }
}
