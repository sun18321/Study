package com.sun.mystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sun.util.GlideRoundTransform;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlideActivity extends AppCompatActivity {

    @BindView(R.id.common)
    Button mCommon;
    @BindView(R.id.circle)
    Button mCircle;
    private ImageView mImg;
    private String url = "http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg";
    private int url_local = R.mipmap.p20;
    private int url_glide = R.mipmap.glide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);

        mImg = (ImageView) findViewById(R.id.image);
        Log.d("size", "init size height:" + mImg.getHeight() + "width:" + mImg.getWidth());

    }

    @OnClick({R.id.common, R.id.circle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common:
                Glide.with(this).load(url_glide).into(mImg);
                break;
            case R.id.circle:
                Glide.with(this).load(url_glide).centerCrop().transform(new GlideRoundTransform(this, 15)).into(mImg);
                Log.d("size", "width:" + mImg.getWidth() + "height:" + mImg.getHeight());

//                Glide.with().load().
                break;
        }
    }
}
