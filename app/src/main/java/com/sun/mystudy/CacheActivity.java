package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.GridView;

import com.sun.cache.Images;
import com.sun.cache.PhotoWallAdapter;

public class CacheActivity extends AppCompatActivity {

    private GridView mPhotoWall;
    private PhotoWallAdapter mAdapter;
    private int mImageThumbSize;
    private int mImageThumbSpacing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        System.out.println("开始了");

        mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
        mImageThumbSpacing = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);
        mPhotoWall = (GridView) findViewById(R.id.photo_wall);
        mAdapter = new PhotoWallAdapter(this, 0, Images.imageThumbUrls, mPhotoWall);
        mPhotoWall.setAdapter(mAdapter);
        mPhotoWall.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int numColumns = (int) Math.floor(mPhotoWall.getWidth() / (mImageThumbSize + mImageThumbSpacing));
                if (numColumns > 0) {
                    int columnWidth = mPhotoWall.getWidth() / numColumns - mImageThumbSpacing;
                    mAdapter.setItemHeight(columnWidth);
                    mPhotoWall.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdapter.fluchCache();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cancelAllTasks();
    }
}
