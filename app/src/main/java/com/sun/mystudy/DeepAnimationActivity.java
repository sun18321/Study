package com.sun.mystudy;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class DeepAnimationActivity extends AppCompatActivity {

    private ImageView mIv_anim;
    private ValueAnimator mAnim_one;
    private ValueAnimator mAnim_two;
    private View mLocation;
    private int mX;
    private int mY;
    private int mIv_x;
    private int mIv_y;
    private int mResult;
    private ImageView mIv_two;
    private ImageView mIv_three;
    private ImageView mIv_four;
    private ImageView mIv_five;
    private ImageView mIv_six;
    private ObjectAnimator mAnim_rotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_animation);

        initView();
    }

    private void initView() {
        mIv_anim = (ImageView) findViewById(R.id.iv_anim);
        mIv_two = (ImageView) findViewById(R.id.iv_anim_two);
        mIv_three = (ImageView) findViewById(R.id.iv_anim_three);
        mIv_four = (ImageView) findViewById(R.id.iv_anim_four);
        mIv_five = (ImageView) findViewById(R.id.iv_anim_five);
        mIv_six = (ImageView) findViewById(R.id.iv_anim_six);

        mLocation = findViewById(R.id.location);

        mAnim_one = new ValueAnimator();
        mAnim_one.setDuration(3000);
        mAnim_one.setObjectValues(new PointF(0, 0));
        mAnim_one.setInterpolator(new LinearInterpolator());
        mAnim_one.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                PointF pointF = new PointF();
                System.out.println("frac" + fraction);

                pointF.x = (mX - mIv_x) * fraction;
                pointF.y = (mY - mIv_y - mResult) * fraction * fraction;
                return pointF;
            }
        });

        mAnim_one.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                mIv_anim.setX(point.x);
                mIv_anim.setY(point.y);

            }
        });

        mAnim_two = new ValueAnimator();
        mAnim_two.setDuration(3000);
        mAnim_two.setStartDelay(500);
        mAnim_two.setObjectValues(new PointF(0, 0));
        mAnim_two.setInterpolator(new LinearInterpolator());
        mAnim_two.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                PointF pointF = new PointF();
                System.out.println("frac" + fraction);

                pointF.x = (mX - mIv_x) * fraction;
                pointF.y = (mY - mIv_y - mResult) * fraction * fraction;
                return pointF;
            }
        });

        mAnim_two.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                mIv_two.setX(point.x);
                mIv_two.setY(point.y);
            }
        });


        mAnim_rotation = ObjectAnimator.ofFloat(mIv_six, "rotation", 0, 360);

        findViewById(R.id.btn_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnim_rotation.start();
            }
        });


//        mAnim_one.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                mIv_anim.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mAnim_two.start();
//                    }
//                }, 500);
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                int[] arr = new int[2];
//                mIv_anim.getLocationOnScreen(arr);
//                System.out.println("结束" + arr[0] + "--" + arr[1]);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });


        findViewById(R.id.btn_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnim_one.start();
                mAnim_two.start();
            }
        });


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        int[] arr = new int[2];
        mLocation.getLocationOnScreen(arr);
        mX = arr[0];
        mY = arr[1];
        int[] arr_iv = new int[2];
        mIv_x = arr_iv[0];
        mIv_y = arr_iv[1];

        System.out.println("点坐标" + mX + "--" + mY);
        System.out.println("图坐标"+mIv_x+"--"+mIv_y);

        mResult = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            mResult = getResources().getDimensionPixelSize(resourceId);
        }
        System.out.println("状态栏" + mResult);
    }
}
