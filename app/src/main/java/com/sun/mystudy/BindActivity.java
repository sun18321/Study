package com.sun.mystudy;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.SurfaceTexture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sun.widget.MyEditText;

public class BindActivity extends AppCompatActivity {

    private TextView mTv_phone;
    private LinearLayout mLl_text;
    private MyEditText mEt_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);

        init();
    }

    private void init() {
        mTv_phone = (TextView) findViewById(R.id.phone_num);
        mLl_text = (LinearLayout) findViewById(R.id.ll_text);
        mEt_input = (MyEditText) findViewById(R.id.phone_input);

        for (int i = 0; i < 11; i++) {
            TextView tv = new TextView(this);
            tv.setText("*");
            if (i == 3) {
                tv.setPadding(20, 0, 0, 0);
            }
            if (i == 7) {
                tv.setPadding(20, 0, 0, 0);
            }
            mLl_text.addView(tv);
        }

        mEt_input.setEditListener(new MyEditText.EditListener() {
            @Override
            public void onChange(CharSequence text) {
                mTv_phone.setText(text);

                ((TextView)mLl_text.getChildAt(0)).setText(text);
            }
        });

//        ValueAnimator.ofFloat()

    }
}
