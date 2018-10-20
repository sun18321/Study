package com.sun.design;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sun.bean.FloatPoint;
import com.sun.bean.ScreenSize;
import com.sun.mystudy.R;

import java.util.ArrayList;
import java.util.List;

public class SixAngleView extends View {
    private final String TAG = "sixangle";

    private ScreenSize mScreenSize;
    private int centerX;
    private int centerY;
    private Paint mPaint;
    private int sideLength = 200;
    private FloatPoint mFirstPoint;
    private FloatPoint mSecondPoint;
    private FloatPoint mThirdPoint;
    private FloatPoint mFourthPoint;
    private FloatPoint mFifthPoint;
    private FloatPoint mSixthPoint;
    private Path mPath;
    private Path mTestPath;
    private float testEndX = 800;
    private float floattextEndY = 800;
    private float mProgress = 0;
    private List<FloatPoint> mList = new ArrayList<>();
    private int index = 0;

    public SixAngleView(Context context) {
        super(context);
        initData();
    }

    public SixAngleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
//        mScreenSize = MyApplication.getScreenSize();

        Log.d(TAG, "init");

        mFirstPoint = new FloatPoint();
        mSecondPoint = new FloatPoint();
        mThirdPoint = new FloatPoint();
        mFourthPoint = new FloatPoint();
        mFifthPoint = new FloatPoint();
        mSixthPoint = new FloatPoint();

        mPaint = new Paint();
        mPath = new Path();
        mTestPath = new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(getResources().getColor(R.color.colorPrimaryOverlay));

        centerX = 600;
        centerY = 600;

        mFirstPoint.setPointX(centerX - sideLength / 2);
        mFirstPoint.setPointY((float) (centerY - sideLength * Math.sin(Math.PI / 3)));
        mSecondPoint.setPointX(centerX + sideLength / 2);
        mSecondPoint.setPointY((float) (centerY - sideLength * Math.sin(Math.PI / 3)));
        mThirdPoint.setPointX(centerX + sideLength);
        mThirdPoint.setPointY(centerY);
        mFourthPoint.setPointX(centerX + sideLength / 2);
        mFourthPoint.setPointY((float) (centerY + sideLength * Math.sin(Math.PI / 3)));
        mFifthPoint.setPointX(centerX - sideLength / 2);
        mFifthPoint.setPointY((float) (centerY + sideLength * Math.sin(Math.PI / 3)));
        mSixthPoint.setPointX(centerX - sideLength);
        mSixthPoint.setPointY(centerY);

        mList.add(mFirstPoint);
        mList.add(mSecondPoint);
        mList.add(mThirdPoint);
        mList.add(mFourthPoint);
        mList.add(mFifthPoint);
        mList.add(mSixthPoint);
        mList.add(mFirstPoint);

        mPath.moveTo(mList.get(index).getPointX(), mList.get(index).getPointY());
        getProgress();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



//        float[] arr = {mFirstPoint.getPointX(), mFirstPoint.getPointY(), mSecondPoint.getPointX(), mSecondPoint.getPointY(), mSecondPoint.getPointX(), mSecondPoint.getPointY()
//                , mThirdPoint.getPointX(), mThirdPoint.getPointY(), mThirdPoint.getPointX(), mThirdPoint.getPointY(), mFourthPoint.getPointX(), mFourthPoint.getPointY(),
//                mFourthPoint.getPointX(), mFourthPoint.getPointY(), mFifthPoint.getPointX(), mFifthPoint.getPointY(), mFifthPoint.getPointX(),
//                mFifthPoint.getPointY(), mSixthPoint.getPointX(), mSixthPoint.getPointY(), mSixthPoint.getPointX(),  mSixthPoint.getPointY()
//                , mFirstPoint.getPointX(), mFirstPoint.getPointY()};
//        canvas.drawLines(arr, mPaint);
//        mPath.moveTo(mFirstPoint.getPointX(), mFirstPoint.getPointY());
//        mPath.lineTo(mSecondPoint.getPointX(), mSecondPoint.getPointY());
//        mPath.lineTo(mThirdPoint.getPointX(), mThirdPoint.getPointY());
//        mPath.lineTo(mFourthPoint.getPointX(), mFourthPoint.getPointY());
//        mPath.lineTo(mFifthPoint.getPointX(), mFifthPoint.getPointY());
//        mPath.lineTo(mSixthPoint.getPointX(), mSixthPoint.getPointY());
//        mPath.lineTo(mFirstPoint.getPointX(), mFirstPoint.getPointY());
////        mPath.setLastPoint(mFirstPoint.getPointX(), mFirstPoint.getPointY());
//        mPath.close();

//        canvas.drawPath(mPath, mPaint);
//        canvas.drawPoint(600, 600, mPaint);

//        Log.d(TAG, "top length:" + (mSecondPoint.getPointX() - mFirstPoint.getPointX()));
//        Log.d(TAG, "most right:" + (mThirdPoint.getPointX() - 600));
//        Log.d(TAG, "height:" + (mFirstPoint.getPointY() - mFifthPoint.getPointY()));
//        Log.d(TAG, "first y:" + mFirstPoint.getPointY() + "six Y:" + mFifthPoint.getPointY());



        if (index < mList.size() - 1) {
            mPath.lineTo(mList.get(index).getPointX() + (mList.get(index + 1).getPointX() - mList.get(index).getPointX()) * mProgress, mList.get(index).getPointY() + (mList.get(index + 1).getPointY() - mList.get(index).getPointY()) * mProgress);
        } else {
            mPath.close();
        }
        canvas.drawPath(mPath, mPaint);
        canvas.drawPoint(600, 600, mPaint);

    }

    private void getProgress() {
        final ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (float) animation.getAnimatedValue();
                 invalidate();
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mProgress = 0;
                index++;
                if (index < mList.size()) {
                    animator.start();
                }
            }

        });
        animator.start();
    }
}
