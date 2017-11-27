package com.sun.mystudy;

import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by sun on 2017/11/18.
 */

public class AnimListener {
    private Context mContext;
    private View mView;
    private ImageView mIv_anim;
    private boolean isAnim = false;
    private ObjectAnimator mAnimator;

    public AnimListener(Context context, View view) {
        mContext = context;
        mView = view;
        initView();
    }

    private void initView() {
        mIv_anim = (ImageView) mView.findViewById(R.id.iv_anim);
        mAnimator = ObjectAnimator.ofFloat(mIv_anim, "translationY", 0, 100);
        mAnimator.setDuration(3000);
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

    }

    public void startAnim() {
        Log.d("animlistener", "" + isAnim);
        if (isAnim) {
        } else {
            mAnimator.start();
        }

    }
}
