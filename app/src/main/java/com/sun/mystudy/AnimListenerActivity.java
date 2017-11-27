package com.sun.mystudy;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.zip.Inflater;

public class AnimListenerActivity extends AppCompatActivity {

    private ImageView mIv_anim;
    private boolean isAnim = false;
    private ObjectAnimator mAnimator;
    private RelativeLayout mRl_anim;
    private View mView_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_listener);

        initView();

    }

    private void initView() {
        mIv_anim = (ImageView) findViewById(R.id.iv_anim);
        mAnimator = ObjectAnimator.ofFloat(mIv_anim, "translationY", 0, 100);
        mAnimator.setDuration(2000);
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnim = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnim = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean running = mAnimator.isRunning();
                Log.d("anim", "" + running);
                if (isAnim) {
                    Toast.makeText(AnimListenerActivity.this, "是true", Toast.LENGTH_SHORT).show();
                } else {
                    mAnimator.start();
                }
            }
        });

        mRl_anim = (RelativeLayout) findViewById(R.id.rl_anim);




        findViewById(R.id.btn_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mView_anim == null) {
                    mView_anim = LayoutInflater.from(AnimListenerActivity.this).inflate(R.layout.anim_item, null);
                    mRl_anim.addView(mView_anim, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

                }

            }
        });
    }
}
