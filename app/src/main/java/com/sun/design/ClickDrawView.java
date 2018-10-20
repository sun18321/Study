package com.sun.design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sun.mystudy.R;

public class ClickDrawView extends View {
    private final String TAG = "clickview";

    private Paint mPaint;
    private Path mPath;
    private float mProgress = (float) 0.1;
    private int mLength = 1000;

    public ClickDrawView(Context context) {
        super(context);
        init();
    }


    public ClickDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Log.d(TAG, "init");

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.colorPrimaryOverlay));
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();

        mPath.moveTo(300, 300);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "ondraw");


        mPath.lineTo(300 + mLength * mProgress, 300 + mLength * mProgress);

        canvas.drawPath(mPath, mPaint);
    }

    public void drawNext() {
        mProgress += 0.1;
        invalidate();
    }
}
