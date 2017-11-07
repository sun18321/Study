package com.sun.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sun on 2017/9/14.
 */

public class FiveCorner extends View {
    public FiveCorner(Context context) {
        super(context);
    }

    public FiveCorner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FiveCorner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width=getWidth();
        int r=width/2;
        canvas.translate(r, r );


        Path path=new Path();

        path.moveTo(0, -r);//A

        path.lineTo(  r* sin(36), r*cos(36));//C

        path.lineTo( -r*sin(72), -r*cos(72) );//E

        path.lineTo(  r*sin(72), -r*cos(72)  );//B

        path.lineTo(  -r*sin(36), r*cos(36)  );//D

        path.close();

        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawPath(path, paint);

        addCoordinateAndMark(canvas);


        ff2(canvas);
    }

    private void addCoordinateAndMark(Canvas canvas) {
        // TODO Auto-generated method stub
        int width=getWidth();
        int r=width/2;

        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        canvas.drawLine(-r  , 0, r, 0, paint);
        canvas.drawLine(0, -r, 0, r, paint);

        paint.setColor(Color.BLUE);
        paint.setTextSize(30);

        r=(int) (r*0.9);
        canvas.drawText("A", 0, -r, paint);
        canvas.drawText("B", r*sin(72), -r*cos(72), paint);
        canvas.drawText("C", r*sin(36), r*cos(36), paint);
        canvas.drawText("D",-r*sin(36), r*cos(36)  , paint);
        canvas.drawText("E", -r*sin(72), -r*cos(72), paint);

    }

    /***
     * 外点 </br>
     x=Rcos(72°*k) </br>
     y=Rsin(72°*k) </br>
     k=0,1,2,3,4 </br>
     </br></br>
     内点 </br>
     r=Rsin(18)/sin(180-36-18) </br>
     x=rcos(72°*k+36°)</br>
     y=rsin(72°*k+36°) </br>
     k=0,1,2,3,4 </br>

     * @param canvas
     */
    private void ff2(Canvas canvas) {
        canvas.rotate(-18);

        float r=getWidth()/2 ;


        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        Path path=new Path();
        path.moveTo(r*cos(72*0), r*sin(72*0));
        for (int i = 1; i <5 ; i++) {
            canvas.drawText(""+i,r*cos(72*i), r*sin(72*i), paint);
            path.lineTo(r*cos(72*i), r*sin(72*i));
        }
        path.close();
        canvas.drawPath(path, paint);


        r=r*sin(18)/sin(180-36-18);
        paint.setColor(Color.CYAN);
        path=new Path();
        path.moveTo(r*cos(72*0+36), r*sin(72*0+36));
        for (int i = 1; i <5 ; i++) {
            canvas.drawText(""+i,r*cos(72*i+36), r*sin(72*i+36), paint);
            path.lineTo(r*cos(72*i+36), r*sin(72*i+36));
        }
        path.close();
        canvas.drawPath(path, paint);
    }

    float cos(int num){
        return (float) Math.cos(num*Math.PI/180);
    }

    float sin(int num){
        return (float) Math.sin(num*Math.PI/180);
    }
}
