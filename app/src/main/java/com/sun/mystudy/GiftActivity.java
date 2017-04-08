package com.sun.mystudy;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.sun.anim.CarDownAnimation;
import com.sun.anim.FireWorkAnimation;

import java.util.zip.Inflater;

public class GiftActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mGiftLayout;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.gift);

        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.RED);
        }

        mInflater = LayoutInflater.from(GiftActivity.this);

        mGiftLayout = (RelativeLayout) findViewById(R.id.anim_gift);
        findViewById(R.id.firework).setOnClickListener(this);
        findViewById(R.id.car).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.firework:
                View view_firework = mInflater.inflate(R.layout.firework, null);
                mGiftLayout.addView(view_firework, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                FireWorkAnimation fireWorkAnimation = new FireWorkAnimation(GiftActivity.this,view_firework.findViewById(R.id.firework_ly));
                fireWorkAnimation.startFireWorkAnimation();
                break;
            case R.id.car:
                View car_up = mInflater.inflate(R.layout.car_up, null);
                View car_down = mInflater.inflate(R.layout.car_down, null);
                mGiftLayout.addView(car_up, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                mGiftLayout.addView(car_down, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                CarDownAnimation carDownAnimation = new CarDownAnimation(GiftActivity.this, car_down.findViewById(R.id.car_3_down));
                carDownAnimation.startCarOnAnimation();
                break;
        }
    }
}
