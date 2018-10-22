package com.sun.design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.BoringLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.sun.mystudy.R;

import java.util.concurrent.LinkedBlockingDeque;

public class StockView extends View {
    private final String TAG = "stock";
    private int mScreenWidth;
    private int mScreenHeight;
    private Paint mAxisPaint;
    private Path mAxisPath;
    private Paint mTracePath;
    private Path mTracePath1;
    private final float mGap = 5;


    public StockView(Context context) {
        super(context);
        init(context);
    }

    public StockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    private void init(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;

        Log.d(TAG, "w:" + mScreenWidth + "h:" + mScreenHeight);

        mAxisPaint = new Paint();
        mAxisPaint.setStyle(Paint.Style.STROKE);
        mAxisPaint.setAntiAlias(true);
        mAxisPaint.setStrokeWidth(3);
        mAxisPaint.setColor(getResources().getColor(R.color.black));
        mAxisPath = new Path();
        mAxisPath.moveTo(mGap, mGap);
        mAxisPath.lineTo(50, 50);

        mTracePath = new Paint();
        mTracePath.setAntiAlias(true);
        mTracePath.setStyle(Paint.Style.STROKE);
        mTracePath.setColor(getResources().getColor(R.color.red));
        mTracePath1 = new Path();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.d(TAG, "meaW:" + getMeasuredWidth() + "meaH:" + getMeasuredHeight());

        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "ondraw");

    }
}
