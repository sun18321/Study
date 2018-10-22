package com.sun.design;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sun.mystudy.R;

public class ClickDrawView extends View {
    private final String TAG = "clickview";

    private Paint mPaint;
    private Path mPath;
    private float mProgress = (float) 0.1;
    private int mLength = 1000;
    private ObjectAnimator mAnimator;

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

        mPath.moveTo(0, 8);


        setPivotX(0);
        setPivotY(0);
        mAnimator = ObjectAnimator.ofFloat(this, "rotation", 0, 360);
        mAnimator.setDuration(1000);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.d(TAG, "w:" + getMeasuredWidth() + "h:" + getMeasuredHeight());

        setMeasuredDimension(100, 16);

        Log.d(TAG, "after w:" + getMeasuredWidth() + "h:" + getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "ondraw");
        mPath.lineTo(50 + mLength * mProgress, 8);
        canvas.drawPath(mPath, mPaint);
    }

    public void drawNext() {
        mProgress += 0.1;
        invalidate();
    }

    public void rotate() {
        Log.d(TAG, "roW:" + getWidth() + "roH:" + getHeight());
        mAnimator.start();
    }
}
