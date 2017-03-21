package com.sun.mystudy;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by stephensun on 2017/3/21.
 */
public class MyRecyclerview extends RecyclerView {
    public MyRecyclerview(Context context) {
        super(context);
    }

    public MyRecyclerview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    private OnItemScrollChangeListener mOnItemScrollChangeListener;
    private View currentView;

    public interface OnItemScrollChangeListener {
        void change(View view, int pisition);
    }

    public void setOnItemScrollChangeListener(OnItemScrollChangeListener listener) {
        mOnItemScrollChangeListener = listener;
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
//        currentView = getChildAt(0);
//        if (mOnItemScrollChangeListener != null) {
//            mOnItemScrollChangeListener.change(currentView, getChildPosition(currentView));
//        }
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//        if (e.getAction() == MotionEvent.ACTION_MOVE) {
//            currentView = getChildAt(0);
//            if (mOnItemScrollChangeListener != null) {
//                mOnItemScrollChangeListener.change(currentView, getChildPosition(currentView));
//            }
//        }
//        return super.onTouchEvent(e);
//    }
}
