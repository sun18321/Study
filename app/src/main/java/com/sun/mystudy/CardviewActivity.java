package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CardviewActivity extends AppCompatActivity {

    private ImageView mImageView;
    private String[] arr = {"http://video.51aso.cn/cover/195459571711080858238.jpg/320","http://video.51aso.cn/cover/159420281711080636539.jpg/320"
    ,"http://video.51aso.cn/cover/118143801711080611539.jpg/320","http://video.51aso.cn/cover/118143801711080607422.jpg/320"};
    private ListView mListview;
    private CardAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);

//        mImageView = (ImageView) findViewById(R.id.card_image_one);
//////        Glide.with(this).load("http://video.51aso.cn/cover/195459571711080858238.jpg/320").into(mImageView);
//        ImageLoader.getInstance().displayImage("http://video.51aso.cn/cover/195459571711080858238.jpg/320", mImageView);

        mListview = (ListView) findViewById(R.id.card_list);
        mAdapter = new CardAdapter(this);
        mListview.setDivider(null);
        mListview.setAdapter(mAdapter);


    }
}
