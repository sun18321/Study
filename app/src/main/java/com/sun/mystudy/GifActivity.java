package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.sun.widget.GifView;

public class GifActivity extends AppCompatActivity {

    private GifView mIv_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);

        init();
    }

    private void init() {

        mIv_1 = (GifView) findViewById(R.id.iv_1);
//        mIv_1.setMovieTime(5000);
        mIv_1.setMovieResource(R.raw.dice_1_1);

    }
}
