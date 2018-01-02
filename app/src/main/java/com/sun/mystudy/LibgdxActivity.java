package com.sun.mystudy;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.BaseLibgdxActivity;
import com.badlogic.gdx.backends.android.DriveUtils;
import com.sun.spine.Dragon;

public class LibgdxActivity extends BaseLibgdxActivity {

    public RelativeLayout mRl_libgdx;
    private Dragon mDragon;
    public View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libgdx);

        Log.d("spine/dragon", "lib_oncreate");

        mRl_libgdx = (RelativeLayout) findViewById(R.id.rl_libgdx);

        initView();
    }

    private void initView() {


        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.r = cfg.g = cfg.b = cfg.a = 8;
        mDragon = new Dragon(this);
        mView = initializeForView(mDragon, cfg);
        if (mView instanceof SurfaceView) {
            SurfaceView glView = (SurfaceView) mView;
            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            glView.setZOrderOnTop(true);
        }



        findViewById(R.id.btn_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DriveUtils.getInstance(LibgdxActivity.this,mDragon).addLibGdx(0);
            }
        });

        findViewById(R.id.btn_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
                cfg.r = cfg.g = cfg.b = cfg.a = 8;
                mDragon = new Dragon(LibgdxActivity.this);
                mView = initializeForView(mDragon, cfg);
                if (mView instanceof SurfaceView) {
                    SurfaceView glView = (SurfaceView) mView;
                    glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
                    glView.setZOrderOnTop(true);
                }

                DriveUtils.getInstance(LibgdxActivity.this,mDragon).addLibGdx(1);

//                mRl_libgdx.addView(mView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            }
        });

        findViewById(R.id.btn_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("dragon", "第三个");

            }
        });

        findViewById(R.id.btn_fourth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DriveUtils.cleanViews();
    }
}
