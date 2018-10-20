package com.sun.design;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.sun.mystudy.R;

public class CountDownView extends View {
    private final String TAG = "countdownview";
    private int i = 0;
    private int mWidgetHeight;
    private int mWidgetWidth;
    private Paint mArcPaint;
    private Paint mBgPaint;
    private Paint mTextPaint;
    private RectF mRectF;
    private float mTextBaseLine;
    private int mProgress = 0;
    private Paint mTestPaint;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(10);
        mArcPaint.setColor(getResources().getColor(R.color.colorPrimaryOverlay));

        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setColor(getResources().getColor(R.color.white));

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setTextSize(100);
        mTextPaint.setColor(getResources().getColor(R.color.black));
        Paint.FontMetrics metrics = mTextPaint.getFontMetrics();
        mTextBaseLine = metrics.descent + (metrics.bottom - metrics.top) / 2;

        mTestPaint = new Paint();
        mTestPaint.setAntiAlias(true);
        mTestPaint.setStyle(Paint.Style.STROKE);
        mTestPaint.setStrokeWidth(4);
        mTestPaint.setColor(getResources().getColor(R.color.white));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mWidgetHeight = getHeight();
        mWidgetWidth = getWidth();

        if (mRectF == null) {
            mRectF = new RectF();
            mRectF.left = 5;
            mRectF.right = mWidgetWidth - 5;
            mRectF.top = 5;
            mRectF.bottom = mWidgetHeight - 5;
        }


//        canvas.drawRect(mRectF, mTestPaint);
        canvas.drawArc(mRectF, -90, mProgress, false, mArcPaint);
        canvas.drawCircle(mWidgetWidth / 2, mWidgetHeight / 2, mWidgetHeight / 2 - 10, mBgPaint);
        canvas.drawText("跳过", mWidgetWidth / 2 - getTextX(), mWidgetHeight / 3 + mTextBaseLine, mTextPaint);


    }

    private float getTextX() {
        Rect bounds = new Rect();
        mTextPaint.getTextBounds("跳过", 0, "跳过".length(), bounds);
        return bounds.width() / 2;
    }

    public void startTime() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 360);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (int) animation.getAnimatedValue();
                invalidate();
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                Toast.makeText(getContext(), "时间到了", Toast.LENGTH_SHORT).show();
            }
        });
        animator.start();
    }
}
