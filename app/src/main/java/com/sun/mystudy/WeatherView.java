package com.sun.mystudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by stephensun on 2017/3/25.
 */
public class WeatherView extends View {

    private Paint mArcPaint;
    private Paint mLinePaint;
    private Paint mTextPaint;
    private Paint mPointPaint;
    private float mWidth;
    private float mHeight;
    private float radius;

    private int startAngle;//圆弧开始角
    private int sweepAngle;//圆弧总角度数
    private int count;//圆弧被分的份数

    private int currentTemp;//当前温度
    private int maxTemp;
    private int minTemp;
    private Bitmap bitmap;
    private int ocAngle;//0度初始角
    private int fgAngle;//总覆盖的角
    private int offset;


    public WeatherView(Context context) {
        this(context, null);
    }

    public WeatherView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {

        initPaint();
    }

    private void initPaint() {
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.WHITE);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(2);


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int defaultSize = 800;

        mWidth = getSize(defaultSize, widthMeasureSpec);
        mHeight = getSize(defaultSize, heightMeasureSpec);


//        mWidth = widthMeasureSpec;
//        mHeight = heightMeasureSpec;

        System.out.println("width" + mWidth);
        System.out.println("height" + mHeight);

    }

    private float getSize(int defaultSize, int measureSpec) {
        float result;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = defaultSize;
        }
        return result;
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//    }






    public int getRealColor(int minTemp,int maxTemp){
        if(maxTemp<=0){
            return Color.parseColor("#00008B");//深海蓝
        }else if(minTemp<=0 && maxTemp>0){
            return Color.parseColor("#4169E1");//黄君兰
        }else if(minTemp>0 && minTemp<15 ){
            return Color.parseColor("#40E0D0");//宝石绿
        }else if(minTemp>=15 && minTemp<25){
            return Color.parseColor("#00FF00");//酸橙绿
        }else if(minTemp>=25 &&minTemp<30){
            return Color.parseColor("#FFD700");//金色
        }else  if(minTemp>=30){
            return Color.parseColor("#CD5C5C");//印度红
        }
        return Color.parseColor("#00FF00");//酸橙绿;
    }
}
