package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GalleryActivity extends AppCompatActivity {

    private int[] arr_image = {R.mipmap.g1, R.mipmap.g2, R.mipmap.g3, R.mipmap.g4, R.mipmap.g5, R.mipmap.g6, R.mipmap.g7, R.mipmap.g8};

    private MyRecyclerview mRecyclerView;
    private CommonRecyclerviewAdapter mAdpter;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        init();

        mRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(this, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Glide.with(GalleryActivity.this).load(arr_image[position]).into(mImageView);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }) {
        });



        mRecyclerView.setOnItemScrollChangeListener(new MyRecyclerview.OnItemScrollChangeListener() {
            @Override
            public void change(View view, int pisition) {
                Glide.with(GalleryActivity.this).load(arr_image[pisition]).into(mImageView);
            }
        });

//        mAdpter.setRecyclerListener(new CommonRecyclerviewAdapter.RecyclerClickListener() {
//            @Override
//            public void onClick(int position) {
//                Glide.with(GalleryActivity.this).load(arr_image[position]).into(mImageView);
//            }
//        });


        mRecyclerView.setAdapter(mAdpter);

    }

    private void init() {
        mImageView = (ImageView) findViewById(R.id.image);
        mRecyclerView = (MyRecyclerview) findViewById(R.id.recyclerview);
        mAdpter = new CommonRecyclerviewAdapter(GalleryActivity.this, R.layout.item_image, arr_image);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(GalleryActivity.this,LinearLayoutManager.HORIZONTAL,false));
    }
}
