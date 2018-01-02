package com.sun.mystudy;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.sun.copy_gdx.GdxAdapter;
import com.sun.copy_gdx.VaeBaseActivity;

public class CopyGdxActivity extends VaeBaseActivity {
    private GdxAdapter gdxAdapter;

    private FrameLayout frame;

    private View view;

    @Override
    protected int getContentViewID() {
        return R.layout.activity_copy_gdx;
    }

    @Override
    protected void beforeInitView() {
        gdxAdapter = new GdxAdapter(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        frame = findViewByIdNoCast(R.id.frame);
    }

    @Override
    protected void initData() {
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.r = cfg.g = cfg.b = cfg.a = 8;
        view = initializeForView(gdxAdapter, cfg);


        if (view instanceof SurfaceView) {
            SurfaceView glView = (SurfaceView) view;
            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            glView.setZOrderOnTop(true);
        }

        fun();

    }

    public void fun() {
        final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frame.addView(view, params);
        frame.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onViewClick(View v) {

    }
}
