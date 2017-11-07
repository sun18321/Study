package com.sun.mystudy;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BoxActivity extends AppCompatActivity {

    private ImageView mIv_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box);

        initView();
    }

    private void initView() {
        mIv_one = (ImageView) findViewById(R.id.iv_one);
        mIv_one.setImageResource(R.drawable.open_box);
        final AnimationDrawable animationDrawable= (AnimationDrawable) mIv_one.getDrawable();
//        animationDrawable.start();
        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.start();
            }
        });

        findViewById(R.id.switch_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIv_one.setImageResource(R.drawable.open_box);
                AnimationDrawable anim_open = (AnimationDrawable) mIv_one.getDrawable();
                anim_open.start();
            }
        });

        findViewById(R.id.switch_shake).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIv_one.setImageResource(R.drawable.shake_box);
                AnimationDrawable anim_shake = (AnimationDrawable) mIv_one.getDrawable();
                anim_shake.start();
            }
        });

    }
}
