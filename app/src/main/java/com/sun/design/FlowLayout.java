package com.sun.design;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {
    private final String TAG = "flowlayout";

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.d(TAG, "measure");

        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int lineWidth = 0;
        int lineHeight = 0;
        int width = 0;
        int height = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams lp = getMarginParams(child);
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (i == 0 || i == 1) {
                Log.d(TAG, "i am num " + i + ":" + childHeight + "");
            }

            if (lineWidth + childWidth > measureWidth) {
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth = childWidth;
                width = Math.max(lineWidth, width);
                height += lineHeight;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            if (i == count - 1) {
                height += lineHeight;
            }
        }
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? measureWidth : width, heightMode == MeasureSpec.EXACTLY ? measureHeight : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Log.d(TAG, "layout");

        int count = getChildCount();
        int lineWidth = 0;
        int lineHeight = 0;
        int top = 0, left = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            MarginLayoutParams lp = getMarginParams(child);

            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (lineWidth + childWidth > getMeasuredWidth()) {
                left = 0;
                top += lineHeight;
                lineHeight = childHeight;
                lineWidth = childWidth;
            } else {
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;
            }

            child.layout(left + lp.leftMargin, top + lp.topMargin, lineWidth - lp.rightMargin, top + lineHeight - lp.bottomMargin);
            left += childWidth;
        }
    }

    private MarginLayoutParams getMarginParams(View view) {
        MarginLayoutParams lp = null;
        if (view.getLayoutParams() instanceof MarginLayoutParams) {
            lp = (MarginLayoutParams) view.getLayoutParams();
        }
        return lp;
    }
}
