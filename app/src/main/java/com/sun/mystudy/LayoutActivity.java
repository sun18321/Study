package com.sun.mystudy;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sun.util.Utils;

public class LayoutActivity extends AppCompatActivity {

    private RelativeLayout mRl_father;
    private ImageView mIv;
    private Button mBtn_1;
    private int mHeight;
    private int mWidth;
    private int screenWidth;
    private int screenHeight;
    private Button mBtn_2;
    private Button mBtn_3;
    private Button mBtn_4;
    private ImageView mIv_cen;
    private ImageView mIv_test;
    private SoundPool mSoundPool;
    private int mPlayId;
    private Button mBtn_5;
    private Button mBtn_6;
    private ScaleAnimation mScaleAnimation_appear;
    private Button mBtn_7;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mIv_test.startAnimation(mScaleAnimation_appear);
                    break;
            }
        }
    };
    private Button mBtn_8;
    private Button mLocation;
    private Button mOnlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        init();
    }
    private void init() {
        WindowManager wm2 = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        screenWidth = wm2.getDefaultDisplay().getWidth();
        screenHeight = wm2.getDefaultDisplay().getHeight();

        System.out.println("新的宽" + screenWidth + "新的高" + screenHeight);

        mRl_father = (RelativeLayout) findViewById(R.id.rl_father);
        mIv = (ImageView) findViewById(R.id.iv);
        mBtn_1 = (Button) findViewById(R.id.btn_1);
        mBtn_2 = (Button) findViewById(R.id.btn_2);
        mIv_test = (ImageView) findViewById(R.id.iv_test);

        mBtn_3 = (Button) findViewById(R.id.btn_3);
        mBtn_4 = (Button) findViewById(R.id.btn_4);
        mIv_cen = (ImageView) findViewById(R.id.center_iv);

        mBtn_5 = (Button) findViewById(R.id.btn_5);
        mBtn_6 = (Button) findViewById(R.id.btn_6);

        mBtn_7 = (Button) findViewById(R.id.btn_7);
        mBtn_8 = (Button) findViewById(R.id.btn_8);

        mOnlayout = (Button) findViewById(R.id.onlayout);
        mLocation = (Button) findViewById(R.id.location);

        mOnlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(mWidth, mHeight);
//                layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM);
//                layoutParams.bottomMargin = Utils.dip2px(LayoutActivity.this, -mHeight);
                layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM);
                layoutParams.topMargin = 1000;
                mRl_father.setLayoutParams(layoutParams);
            }
        });


        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] arr = new int[2];
                mRl_father.getLocationOnScreen(arr);
                System.out.println("坐标x" + arr[0] + "坐标y" + arr[1]);
            }
        });

        if (mSoundPool == null) {
            mSoundPool = new SoundPool(5, AudioManager.STREAM_SYSTEM, 5);
        }

        mBtn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRl_father.setVisibility(View.GONE);
            }
        });

        mBtn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRl_father.setVisibility(View.VISIBLE);
            }
        });

        mBtn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIv_test.startAnimation(mScaleAnimation_appear);
            }
        });

        mBtn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendEmptyMessage(0);
            }
        });

        ObjectAnimator test_anim = ObjectAnimator.ofFloat(mIv_test, "translationX", 0, 500, 0, -500, 0);
        test_anim.setDuration(8000);
        test_anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mSoundPool.load(LayoutActivity.this, R.raw.guesssuccesssound, 0);
                mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                        mPlayId = mSoundPool.play(1, 1, 1, 1, -1, 1);
                    }
                });
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mSoundPool.stop(mPlayId);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
//        test_anim.start();

        TranslateAnimation translateAnimation = new TranslateAnimation(0, 500, 0, 0);
        translateAnimation.setDuration(5000);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mSoundPool.load(LayoutActivity.this, R.raw.guesssuccesssound, 0);
                mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                        mPlayId = mSoundPool.play(1, 1, 1, 1, -1, 1);
                    }
                });
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mSoundPool.stop(mPlayId);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        mIv_test.startAnimation(translateAnimation);

        mScaleAnimation_appear = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mScaleAnimation_appear.setDuration(5000);
        mScaleAnimation_appear.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mSoundPool.load(LayoutActivity.this, R.raw.guesssuccesssound, 0);
                mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                        mPlayId = mSoundPool.play(1, 1, 1, 1, -1, 1);
                    }
                });
           }

            @Override
            public void onAnimationEnd(Animation animation) {
                mSoundPool.stop(mPlayId);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        mIv_test.startAnimation(mScaleAnimation_appear);


        mBtn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(mIv_cen, "translationX", 0, 100);
                animator.start();
            }
        });

        mBtn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(mIv_cen, "translationX", 100, 0);
                animator.start();
            }
        });

        WindowManager wm = getWindowManager();
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        int x = point.x;
        int y = point.y;
        System.out.println("屏幕宽" + x + "屏幕高" + y);

        mBtn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("得到的高度" + mHeight);
                ObjectAnimator animator = ObjectAnimator.ofFloat(mRl_father, "translationY", 0, mHeight);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
//                        mHandler.sendEmptyMessageDelayed(0, 2000);
                        System.out.println("下移结束了");
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.start();
            }
        });

        mBtn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(mRl_father, "translationY", mHeight, 0);
                animator.start();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mWidth = mRl_father.getWidth();
        mHeight = mRl_father.getHeight();
        System.out.println("宽" + mWidth + "高" + mHeight);
    }
}
