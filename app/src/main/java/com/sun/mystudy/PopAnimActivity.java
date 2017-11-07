package com.sun.mystudy;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.sun.util.Utils;

public class PopAnimActivity extends AppCompatActivity {

    private Button mBtn_click;
    private PopupWindow mPopupWindow;
    private View mContentview;
    private ImageView mIv_one;
    private Button mBtn_anim;
    private ObjectAnimator mAnimator;
    private RelativeLayout mRl_top;
    private int mX;
    private int mY;
    private Button mBtn_dismiss;
    private int mResult;
    private ImageView mIv_trans;
    private ObjectAnimator mTranslationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_anim);

        initView();

    }

    private void initView() {

        mBtn_click = (Button) findViewById(R.id.btn_click);
        mBtn_anim = (Button) findViewById(R.id.btn_anim);
        mRl_top = (RelativeLayout) findViewById(R.id.rl_top);
        mBtn_dismiss = (Button) findViewById(R.id.btn_dismiss);
        mIv_trans = (ImageView) findViewById(R.id.trans);
        mIv_trans.bringToFront();

        mTranslationY = ObjectAnimator.ofFloat(mIv_trans, "translationY", 0, 2000);
        mTranslationY.setDuration(5000);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTranslationY.start();
            }
        });

        mBtn_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            }
        });

        mPopupWindow = new PopupWindow();
        mContentview = LayoutInflater.from(this).inflate(R.layout.pop_content, null);
        mPopupWindow.setContentView(mContentview);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);


        mIv_one = (ImageView) mContentview.findViewById(R.id.iv_one);
        mAnimator = ObjectAnimator.ofFloat(mIv_one, "translationY", 0, 800);
        mAnimator.setDuration(4000);
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
//                mIv_one.bringToFront();
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        mBtn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.showAtLocation(mBtn_click, Gravity.CENTER, 0, 0);
            }
        });

        mBtn_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVIew();


//                mAnimator.start();
            }
        });
    }

    private void addVIew() {
        int[] arr = new int[2];
        mIv_one.getLocationOnScreen(arr);
        mX = arr[0];
        mY = arr[1];
        System.out.println("iv坐标" + "X:" + mX + "Y:" + mY);



        ImageView iv = new ImageView(PopAnimActivity.this);
        iv.setImageResource(R.mipmap.g5);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Utils.dip2px(PopAnimActivity.this, (float) 50),
                Utils.dip2px(PopAnimActivity.this, (float) 50));
        layoutParams.leftMargin = 0;
        layoutParams.topMargin = mY - mResult;
        mRl_top.addView(iv, layoutParams);
        iv.bringToFront();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        mResult = 0;
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                mResult = getResources().getDimensionPixelSize(resourceId);
            }

    }
}
